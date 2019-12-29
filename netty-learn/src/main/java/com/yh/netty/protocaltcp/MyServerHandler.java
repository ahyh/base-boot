package com.yh.netty.protocaltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        //接收到数据
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务端接收到的信息：len: " + len + "内容:" + new String(content, Charset.forName("utf-8")));
        System.out.println("服务端接收消息数量: " + (++count));

        //回复消息
        String rsp = UUID.randomUUID().toString();
        int rspLen = rsp.getBytes("utf-8").length;
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(rspLen);
        messageProtocol.setContent(rsp.getBytes("utf-8"));
        ctx.writeAndFlush(messageProtocol);
    }
}
