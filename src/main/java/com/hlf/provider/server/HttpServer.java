package com.hlf.provider.server;

import com.hlf.provider.servlet.DispatcherServlet;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * rpc框架提供的服务器类型     tomcat jetty  netty ....
 */
public class HttpServer {
    public void start(String hostName, Integer port) {
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);
        tomcat.getService().addConnector(connector);

        StandardEngine engine = new StandardEngine();
        engine.setDefaultHost(hostName);

        Host host = new StandardHost();
        host.setName(hostName);

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        //注册servlet进行请求的处理
        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
        // tomcat.addServlet(contextPath, "dispatcher2", new DefinedServlet());
        // context.addServletMappingDecoded("/*", "dispatcher2");
        context.addServletMappingDecoded("/*", "dispatcher");
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException ex) {
            ex.printStackTrace();
        }

    }
}
