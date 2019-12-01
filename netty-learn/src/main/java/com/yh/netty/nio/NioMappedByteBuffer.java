package com.yh.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer直接将文件映射到内存
 *
 * @author yanhuan
 */
public class NioMappedByteBuffer {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("file1.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 通过channel获取文件在内存（堆外内存）映射
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(1, (byte) 'H');

        channel.close();
        randomAccessFile.close();
    }
}
