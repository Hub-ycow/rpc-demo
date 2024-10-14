package com.hlf.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hlf.consumer.proxy.ProxyFactory;
import com.hlf.provider.service.HelloService;

public class ConsumerApplication {

    public static void main(String[] args) throws ClassNotFoundException, JsonProcessingException {

        //初版
       /* HttpClient httpClient = new HttpClient();
        String send = (String) httpClient.send("localhost", 8080, new Invocation(HelloService.class.getName(),
                "sayHello",
                new Class[]{String.class},
                new Object[]{"hlf"}));
        System.out.println(send);*/
        //要是可以像调用接口一样调用服务就好了
        HelloService helloService = ProxyFactory.getProxy(HelloService.class, "V2");
        String result = helloService.sayHello("hlf");
        System.out.println(result);
    }
}
