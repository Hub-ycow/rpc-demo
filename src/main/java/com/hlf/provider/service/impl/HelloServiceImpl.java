package com.hlf.provider.service.impl;

import com.hlf.rpc.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
