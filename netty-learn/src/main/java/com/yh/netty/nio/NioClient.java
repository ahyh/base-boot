package com.yh.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NioClient
 *
 * @author yanhuan
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        //提供服务器端的IP和port
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7777);

        //连接服务器
        if (!socketChannel.connect(address)) {
            while(!socketChannel.finishConnect()){
                System.out.println("Client need time to Connect, but not blocking");
            }
        }

        //如果连接成功可以发送数据
        ByteBuffer buffer = ByteBuffer.wrap("hello selector".getBytes());

        //发送数据，将buffer数据写入Channel
        socketChannel.write(buffer);

        System.in.read();
    }
}
