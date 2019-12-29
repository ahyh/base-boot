package com.yh.netty.rpc.provider;

import com.yh.netty.rpc.api.HelloService;

public class HelloServiceImpl implements HelloService {

    /**
     * 服务提供方接口实现
     */
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息: " + msg);
        if (msg != null) {
            return "已经收到消息:" + msg;
        }
        return "收到空消息";
    }
}
