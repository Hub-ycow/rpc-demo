package com.hlf.consumer.proxy;

import com.hlf.bean.Invocation;
import com.hlf.consumer.client.HttpClient;
import com.hlf.provider.registry.LocalRegistry;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.List;

public class ProxyFactory {
    public static <T> T getProxy(Class<? extends T> interfaceClass, String version) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, (proxy, method, args) -> {
            String name = interfaceClass.getName();
            //进行相关api的调用
            List<String> urlList = LocalRegistry.get(interfaceClass.getName());
            URL random = LocalRegistry.random(urlList);
            HttpClient httpClient = new HttpClient();
            String send = (String) httpClient.send(random, new Invocation(name,
                    method.getName(),
                    method.getParameterTypes(),
                    args, version));
            return send;
        });
    }
}
