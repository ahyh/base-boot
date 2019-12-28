package com.yh.netty.handlerchain;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //加入出站的handler，对数据进行编码
        pipeline.addLast(new MyLong2ByteEncoder());

        //加入入站的Handler，对数据进行解码
        pipeline.addLast(new MyByte2LongDecoder());

        //加入一个自定义的Handler，处理业务
        pipeline.addLast(new MyClientHandler());
    }
}
