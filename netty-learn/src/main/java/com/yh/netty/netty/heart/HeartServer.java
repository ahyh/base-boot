package com.yh.netty.netty.heart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 心跳检测Server
 *
 * @author yanhuan
 */
public class HeartServer {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入一个netty提供的一个IdleStateHandler,netty提供的处理空闲状态的处理器
                            //readerIdleTime：多长时间没有read操作，触发一个事件，发送一个心跳检测包检测是否连接
                            //writerIdleTime：多长时间没有write操作，触发一个事件，发送一个心跳检测包检测是否连接
                            //allIdleTime：多长时间没有read/write操作，触发一个事件，发送一个心跳检测包检测是否连接
                            pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                            //当IdleEvent触发后，就会传递给pipeLine的下一个Handler处理，调用下一个Handler的userEventTrigger
                            pipeline.addLast(new HeartIdleHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(8889).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
