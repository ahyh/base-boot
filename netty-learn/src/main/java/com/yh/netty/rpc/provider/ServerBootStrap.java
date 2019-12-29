package com.yh.netty.rpc.provider;

import com.yh.netty.rpc.netty.NettyServer;

/**
 * 服务提供者
 */
public class ServerBootStrap {

    public static void main(String[] args) throws Exception {
        NettyServer.startServer("127.0.0.1", 8889);
    }
}
