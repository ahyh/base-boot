package com.yh.netty.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

public class BioCopyClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7777);
        String filename = "star.mp3";
        FileInputStream fileInputStream = new FileInputStream(filename);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];
        long readCount;
        long total = 0;
        long startTime = System.currentTimeMillis();
        while ((readCount = fileInputStream.read(bytes)) >= 0) {
            total += readCount;
            dataOutputStream.write(bytes);
        }
        System.out.println("send bytes total:" + total + " cost time:" + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        fileInputStream.close();
    }
}
