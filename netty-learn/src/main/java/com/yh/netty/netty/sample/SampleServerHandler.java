package com.yh.netty.netty.sample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 自定义一个Handler，需要继承Netty规定好的HandlerAdapter
 *
 * @author yanhuan
 */
public class SampleServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据方法
     *
     * @param ctx 上下文对象，包含管道（包含多个Handler）和通道（数据的读写）、地址（address）
     * @param msg 客户端发送的数据，默认是Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**
         * 如果这里有一个特别耗时的操作，需要异步执行，将任务添加到taskQueue中
         * 添加到对应的NioEventLoop的taskQueue中
         * 解决方案1-
         */
        /**
         * 解决方案1-用户自定义的普通任务，提交到eventLoop对应的taskQueue中
         */
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello client1", CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * 解决方案2-用户提交定时任务到scheduleTaskQueue
         */
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello client3", CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println("Server read data thread:" + Thread.currentThread().getName());
        System.out.println("Server ctx:" + ctx);
        //将msg转成ByteBuf, netty提供的, 性能更高
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Client send msg:" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("Client address" + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕后
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // write + flush，将数据写入到缓冲并刷新
        // 一般来讲需要对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Client!", CharsetUtil.UTF_8));
    }

    /**
     * 如果出现异常，关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
