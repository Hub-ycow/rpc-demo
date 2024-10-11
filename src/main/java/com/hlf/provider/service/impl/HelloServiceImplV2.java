package com.hlf.provider.service.impl;

import com.hlf.provider.service.HelloService;

public class HelloServiceImplV2 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "你好" + name;
    }
}
