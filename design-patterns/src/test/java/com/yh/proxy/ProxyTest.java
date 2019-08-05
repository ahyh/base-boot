package com.yh.proxy;

import org.junit.Test;

public class ProxyTest {

    @Test
    public void testHelloServiceProxy() {
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy(new HelloServiceImpl());
        HelloService proxy = helloServiceProxy.getProxy();
        String s = proxy.sayHello(" world");
        System.out.println(s);
    }

    @Test
    public void testServiceProxy() {
        ServiceProxy<HelloService> serviceProxy = new ServiceProxy<>(HelloService.class);
        HelloService proxy = serviceProxy.getProxy();
        String s = proxy.sayHello(" world");
        System.out.println(s);
    }
}
