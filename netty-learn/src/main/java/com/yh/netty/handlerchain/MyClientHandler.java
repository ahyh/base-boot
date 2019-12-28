package com.yh.netty.handlerchain;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("服务器IP: " + ctx.channel().remoteAddress());
        System.out.println("MyClientHandler channelRead0 msg: " + msg);

    }

    /**
     * 连接建立时发送数据
     *
     * @param ctx 上下文对象
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler发送数据");
        ctx.writeAndFlush(123456L);
    }
}
