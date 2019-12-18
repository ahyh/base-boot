package com.yh.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 定义一个Channel组，管理所有的Channel
     * GlobalEventExecutor.INSTANCE是一个全局事件执行器，是一个单例对象
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 一旦连接建立后，第一个被执行
     * 当当前的Channel加入到channelGroup
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将加入聊天的信息推送给其他客户端
        channelGroup.writeAndFlush(sdf.format(new Date()) + "[客户端]" + channel.remoteAddress() + "加入聊天");
        channelGroup.add(channel);
    }

    /**
     * 断开连接触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(sdf.format(new Date()) + "[客户端]" + channel.remoteAddress() + "离开");
        System.out.println("channelGroup size:" + channelGroup.size());
    }

    /**
     * 表示channel处于活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(sdf.format(new Date()) + ctx.channel().remoteAddress() + "上线了");
    }

    /**
     * 表示channel处于不活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(sdf.format(new Date()) + ctx.channel().remoteAddress() + "下线了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        //遍历channelGroup
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(sdf.format(new Date()) + "[客户端]" + channel.remoteAddress() + "发送消息:" + msg);
            } else {
                ch.writeAndFlush(sdf.format(new Date()) + "[我]:" + msg);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
