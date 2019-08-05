package com.yh.proxy;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String context) {
        return "hello" + context;
    }
}
