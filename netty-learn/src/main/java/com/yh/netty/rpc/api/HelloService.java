package com.yh.netty.rpc.api;

/**
 * 公共接口,服务提供方和服务消费方共用
 *
 * @author yanhuan
 */
public interface HelloService {

    String hello(String msg);
}
