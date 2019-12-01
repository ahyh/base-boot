package com.yh.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIOServer
 * 理解Selector、Channel、Buffer之间
 *
 * @author yanhuan
 */
public class NioServer {

    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //获取一个Selector对象
        Selector selector = Selector.open();

        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(7777));

        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //serverSocketChannel注册到selector上, 关系连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true) {
            //等待1秒，如果没有Accept事件发生，返回
            if (selector.select(1000) == 0) {
                System.out.println("Server wait 1 second");
                continue;
            }
            //如果返回大于0，就获取到相关的SelectionKey集合
            //返回关注的事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                //获取到事件
                SelectionKey key = keyIterator.next();
                //根据key对应的通道发生的事件做相应的处理
                if (key.isAcceptable()) {
                    //如果是一个连接事件,生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置为非阻塞的SocketChannel
                    socketChannel.configureBlocking(false);
                    System.out.println("Client connect success, a socketChannel created");
                    //将当前的socketChannel也注册到Selector上,关注事件为Read，关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {
                    //如果是read事件,通过key获取对应的Channel
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //获取到Channel关联的Buffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    //读取Channel中
                    socketChannel.read(byteBuffer);
                    System.out.println("from client:" + new String(byteBuffer.array()));
                }
                //手动从selector中移除当前的SelectionKey,防止重复执行
                keyIterator.remove();
            }
        }
    }
}
