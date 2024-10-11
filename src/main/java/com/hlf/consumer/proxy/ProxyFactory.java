package com.hlf.consumer.proxy;

import com.hlf.bean.Invocation;
import com.hlf.consumer.client.HttpClient;
import com.hlf.provider.registry.LocalRegistry;
import com.hlf.provider.service.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.List;

public class ProxyFactory {
    public static <T> T getProxy(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = interfaceClass.getName();
                List<URL> list = LocalRegistry.get(name);
                URL random = new URL("http", "localhost", 8080, "/");


                HttpClient httpClient = new HttpClient();
                String send = (String) httpClient.send(random, new Invocation(HelloService.class.getName(),
                        method.getName(),
                        method.getParameterTypes(),
                        args,"V2"));
                return send;
            }
        });
    }
}
