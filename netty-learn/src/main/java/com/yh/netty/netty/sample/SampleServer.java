package com.yh.netty.netty.sample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * SampleServer
 *
 * @author yanhuan
 */
public class SampleServer {

    public static void main(String[] args) throws Exception {
        /**
         * 1-创建bossGroup和workerGroup
         * bossGroup只是处理连接请求，真正和客户端的业务处理会交给workerGroup完成
         * bossGroup和workerGroup都是无限循环
         * bossGroup和workerGroup含有的子线程个数默认实际是CPU核数 * 2
         */
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /**
             * 2-创建服务器端启动对象，配置参数
             *
             */
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)                           //设置线程组
                    .channel(NioServerSocketChannel.class)                    //使用NioSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)              //设置线程队列得到的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)      //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {   //给workerGroup的EventLoop对应的管道设置处理器
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new SampleServerHandler());
                        }
                    });

            System.out.println("Server is ready...");

            //绑定一个端口并同步出去，生成一个ChannelFuture对象
            ChannelFuture channelFuture = bootstrap.bind(8889).sync();

            //给channelFuture注册监听器，监控业务关心的事件
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("监听端口成功");
                    } else {
                        System.out.println("监听端口失败");
                    }
                }
            });

            //对关闭通道进行监听,不是关闭通道，而是监听关闭通道事件
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
