package com.yh.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO模型
 *
 * @author yanhuan
 */
public class BioServer {

    /**
     * 线程池机制，一个连接对应一个线程来处理
     */
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("Server Start!");

        while (true) {
            //监听，等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("connect to a client!");

            executorService.execute(() -> {
                //可以和客户端通讯
                handle(socket);
            });

        }
    }

    public static void handle(Socket socket) {
        byte[] bytes = new byte[1024];
        //通过socket获取输入流
        try {
            InputStream inputStream = socket.getInputStream();
            //循环读取数据
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read, Charset.defaultCharset()));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("close connect to client");
            try {
                //关闭连接
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
