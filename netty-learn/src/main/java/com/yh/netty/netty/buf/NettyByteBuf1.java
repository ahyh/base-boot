package com.yh.netty.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * ByteBuf
 *
 * @author yanhuan
 * 不需要flip反转就可以即可以写，也可以读
 * 底层维护的readIndex,writeIndex,capacity将buffer分成3部分
 */
public class NettyByteBuf1 {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        //输入
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }
        System.out.println("capacity:" + buffer.capacity());
        //输出,readIndex不会改变
        for (int i = 0; i < buffer.capacity(); i++) {
            byte aByte = buffer.getByte(i);
            System.out.println(aByte);
        }
        //输出,readIndex会改变,递增
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.readByte();
            System.out.println(b);
        }
    }
}
