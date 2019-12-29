package com.yh.netty.rpc.consumer;

import com.yh.netty.rpc.api.HelloService;
import com.yh.netty.rpc.netty.NettyClient;

public class ClientBootStrap {

    public static final String PROTOCOL_PREFIX = "HelloService#hello#";

    public static void main(String[] args) {
        //创建消费者
        NettyClient nettyClient = new NettyClient();

        //创建代理对象
        HelloService helloService = (HelloService) nettyClient.getBean(HelloService.class, PROTOCOL_PREFIX);

        //通过代理对象调用服务提供者提供的方法
        String result = helloService.hello("你好 RPC");

        System.out.println(result);
    }
}
