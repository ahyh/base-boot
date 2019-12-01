package com.yh.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel
 *
 * @author yanhuan
 */
public class NioFileChannel {

    public static void main(String[] args) throws Exception {
        writeFileByChannel("file1.txt");
        readFileByChannel("file1.txt");
    }


    public static void readFileByChannel(String path) throws Exception {
        //创建文件输入流
        FileInputStream fileInputStream = new FileInputStream(path);
        //通过输入流获取Channel
        FileChannel channel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //将通道的数据读取到buffer
        channel.read(buffer);
        //将字节转换成字符串
        System.out.println(new String(buffer.array()));
        //关闭流
        fileInputStream.close();
    }


    public static void writeFileByChannel(String path) throws Exception {
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream(path);

        //获取outputStream对应的Channel
        FileChannel channel = fileOutputStream.getChannel();

        //创建一个缓冲
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello, file channel".getBytes());

        //切换buffer读写状态
        buffer.flip();

        //字节写入FileChannel中
        channel.write(buffer);

        //关闭流
        fileOutputStream.close();
    }
}
