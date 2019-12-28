package com.yh.netty.handlerchain;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Byte -> Long Decoder
 *
 * @author yanhuan
 */
public class MyByte2LongDecoder extends ByteToMessageDecoder {

    /**
     * 该方法会被调用多次，直到确定没有新的元素被添加到List中，或者是ByteBuf没有更多的可读字节为止
     * 如果List不为空，就会将list传递给下一个Handler进行处理，该处理器方法也会被调用多次
     *
     * @param ctx 上下文
     * @param in  入站的ByteBuf
     * @param out List集合，将解码后的数据传给下一个Handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByte2LongDecoder decode被调用");
        //因为Long是8个字节大小的，必须大于8个字节才能读取到一个long
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
