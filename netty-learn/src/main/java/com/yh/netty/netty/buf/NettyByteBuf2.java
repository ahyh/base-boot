package com.yh.netty.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyByteBuf2 {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);
        if (buffer.hasArray()) {
            byte[] array = buffer.array();
            //将array转成字符串
            System.out.println(new String(array, CharsetUtil.UTF_8));
        }
    }
}
