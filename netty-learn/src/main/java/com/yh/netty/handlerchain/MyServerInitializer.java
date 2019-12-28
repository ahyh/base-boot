package com.yh.netty.handlerchain;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * ChannelInitializer
 *
 * @author yanhuan
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //入站的Handler解码
        pipeline.addLast(new MyByte2LongDecoder());
        //出站的Handler编码
        pipeline.addLast(new MyLong2ByteEncoder());
        //加入自定义Handler处理业务逻辑
        pipeline.addLast(new MyServerHandler());
    }
}
