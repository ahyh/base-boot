package com.yh.netty.handlerchain;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyLong2ByteEncoder extends MessageToByteEncoder<Long> {

    /**
     * 编码的方法
     *
     * @param ctx 上下文
     * @param msg 数据
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLong2ByteEncoder encode 被调用");
        System.out.println("msg: " + msg);
        out.writeLong(msg);
    }
}
