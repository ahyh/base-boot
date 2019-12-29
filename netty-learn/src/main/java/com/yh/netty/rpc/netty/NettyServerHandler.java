package com.yh.netty.rpc.netty;

import com.yh.netty.rpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送的消息并调用服务
        System.out.println("NettyServerHandler channelRead msg: " + msg);
        //客户端调用服务端的api时，需要定义一个协议
        if (msg.toString().startsWith("HelloService#hello#")) {
            String helloResult = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            //将helloResult返回客户端
            ctx.writeAndFlush(helloResult);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
