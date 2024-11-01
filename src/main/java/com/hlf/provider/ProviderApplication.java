package com.hlf.provider;

import com.hlf.provider.mapping.InterfaceMapping;
import com.hlf.provider.registry.LocalRegistry;
import com.hlf.provider.server.HttpServer;
import com.hlf.rpc.HelloService;
import com.hlf.provider.service.impl.HelloServiceImpl;
import com.hlf.provider.service.impl.HelloServiceImplV2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProviderApplication {
    public static void main(String[] args) {
        try {
            //初始化注册
            List<URL> urlList = new ArrayList<>();
            URL url = new URL("http", "localhost", 8080, "/");
            urlList.add(url);
            LocalRegistry.register(HelloService.class.getName(), urlList);

            //注册自己的实现类
            String[] split1 = HelloServiceImpl.class.getName().split("\\.");
            String[] split2 = HelloServiceImplV2.class.getName().split("\\.");
            InterfaceMapping.put(split1[split1.length - 1], HelloServiceImpl.class);
            InterfaceMapping.put(split2[split2.length - 1], HelloServiceImplV2.class);

            //启动服务器的功能
            HttpServer httpServer = new HttpServer();
            httpServer.start("localhost", 8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
