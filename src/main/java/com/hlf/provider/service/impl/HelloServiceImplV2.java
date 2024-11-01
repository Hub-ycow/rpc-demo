package com.hlf.provider.service.impl;

import com.hlf.rpc.HelloService;

public class HelloServiceImplV2 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "你好" + name;
    }
}
