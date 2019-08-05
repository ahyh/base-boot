package com.yh.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author yanhuan
 */
public class HelloServiceProxy implements InvocationHandler {

    /**
     * 被代理的接口
     */
    private HelloService helloService;

    public HelloServiceProxy(HelloService helloService) {
        super();
        this.helloService = helloService;
    }

    /**
     * 获取代理对象
     *
     * @return HelloService proxy instance
     */
    public HelloService getProxy() {
        return (HelloService) Proxy.newProxyInstance(helloService.getClass().getClassLoader(), helloService.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before proxy method invoke");
        Object invoke = method.invoke(helloService, args);
        System.out.println("after proxy method invoke");
        return invoke;
    }
}
