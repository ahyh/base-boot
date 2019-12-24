package com.yh.netty.netty.heart;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartIdleHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * @param ctx 上下文
     * @param evt 事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;
            switch (event.state()){
                case READER_IDLE:
                    System.out.println("read idle");
                    eventType = "read idle";
                case WRITER_IDLE:
                    System.out.println("write idle");
                    eventType = "write idle";
                case ALL_IDLE:
                    System.out.println("read/write idle");
                    eventType = "read/write idle";
            }

            System.out.println(ctx.channel().remoteAddress() + eventType);
        }
    }
}
