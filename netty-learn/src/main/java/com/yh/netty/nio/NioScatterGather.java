package com.yh.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Channel的分散和聚合
 * Scattering:分散，将数据写入到buffer时，可以采用buffer数组，依次写入
 * Gathering:聚合，从buffer读取数据时，可以采用buffer数组，依次读
 *
 * @author yanhuan
 */
public class NioScatterGather {

    public static void main(String[] args) throws IOException {
        //使用ServerChannel, ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8888);
        //绑定端口到Socket,并启动
        serverSocketChannel.socket().bind(address);

        //创建一个Buffer数组
        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(10);
        buffers[1] = ByteBuffer.allocate(5);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLen = 15;
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLen) {
                socketChannel.read(buffers);
                //累计读取的字节数
                byteRead += 1;
                System.out.println("byteRead:" + byteRead);
            }
            //将所有的buffer翻转
            Arrays.asList(buffers).forEach(Buffer::flip);
            //将数据读出，显示会客户端
            long byteWrite = 0;
            while (byteWrite < messageLen) {
                socketChannel.write(buffers);
                byteWrite += 1;
            }

            //将所有的buffer clean操作
            Arrays.asList(buffers).forEach(Buffer::clear);

            //
            System.out.println("byteRead:" + byteRead + ",byteWrite:" + byteWrite);
        }

    }
}
