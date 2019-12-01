package com.yh.netty.nio;

import java.nio.IntBuffer;

/**
 * Buffer介绍
 *
 * @author yanhuan
 */
public class NioBuffer {

    public static void main(String[] args) {

        //创建一个Buffer，容量大小为5
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer中放入数据
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);

        //从buffer中读取数据
        //将buffer翻转，读写切换
        intBuffer.flip();

        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
