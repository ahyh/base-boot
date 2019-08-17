package com.yh.decorator;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 测试小写InputStream装饰类
 *
 * @author yanhuan
 */
public class LowerCaseInputStreamMain {

    public static void main(String[] args) throws Exception {

        InputStream inputStream = new LowerCaseInputStream(new FileInputStream("D:\\logs\\test.txt"));
        int c;
        byte[] bytes = new byte[1024];
        while ((c = inputStream.read(bytes)) > 0) {
            System.out.println(new String(bytes, "utf-8"));
        }
        inputStream.close();
    }
}
