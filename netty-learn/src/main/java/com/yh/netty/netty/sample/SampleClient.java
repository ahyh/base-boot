package com.yh.netty.netty.sample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * SampleClient
 *
 * @author yanhuan
 */
public class SampleClient {

    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();

        //创建客户端启动对象
        Bootstrap bootstrap = new Bootstrap();

        try {
            /**
             * 设置线程组
             * 设置客户端通道实现类，反射实现
             *
             */
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new SampleClientHandler());
                        }
                    });

            System.out.println("Client is OK!");

            //启动客户端，连接Server
            //ChannelFuture涉及到netty的异常模型
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8889).sync();

            //给关闭通道增加监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
