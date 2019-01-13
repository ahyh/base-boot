package com.yh.service;

import com.yh.properties.HelloProperties;

public class HelloService {

    private HelloProperties helloProperties;

    public String hello(String message) {
        return helloProperties.getPrefix() + "--" + message + "--" + helloProperties.getSuffix();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
