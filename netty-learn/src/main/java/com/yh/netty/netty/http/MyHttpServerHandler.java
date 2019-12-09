package com.yh.netty.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * HttpServerHandler
 *
 * @author yanhuan
 */
public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取事件触发时执行
     *
     * @param ctx 上下文
     * @param msg http数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断msg是不是HttpRequest请求
        if (msg instanceof HttpRequest) {
            System.out.println("msg 类型:" + msg.getClass());
            System.out.println("客户端地址:" + ctx.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.getUri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求/favicon.ico,不做响应");
                return; 
            }

            //回复信息给浏览器
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello, I am Server", CharsetUtil.UTF_8);
            //构造一个Http响应
            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            ctx.writeAndFlush(httpResponse);
        }
    }
}
