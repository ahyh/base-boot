package com.yh.netty.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NioCopyClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(7777));
        String filename = "star.mp3";

        FileInputStream fileInputStream = new FileInputStream(filename);
        FileChannel channel = fileInputStream.getChannel();

        //准备发送数据
        long startTime = System.currentTimeMillis();

        //transferTo底层使用零拷贝
        long transferCount = channel.transferTo(0, channel.size(), socketChannel);

        System.out.println("send total:" + transferCount + " cost time:" + (System.currentTimeMillis() - startTime));
    }
}
