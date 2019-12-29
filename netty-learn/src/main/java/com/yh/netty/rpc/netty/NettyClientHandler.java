package com.yh.netty.rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext channelHandlerContext;

    private String result;

    private String param;

    /**
     * 与服务器连接创建后调用，第一个被调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channelHandlerContext = ctx;
    }

    /**
     * 收到服务器的返回后调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.result = msg.toString();
        //唤醒等待线程
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 被代理对象调用，发送数据给服务器，然后wait，等待被唤醒，返回结果
     *
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        this.channelHandlerContext.writeAndFlush(param);
        //进行wait
        wait();  //等待read方法获取到结果后，唤醒
        return result;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
