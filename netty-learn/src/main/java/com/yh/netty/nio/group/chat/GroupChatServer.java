package com.yh.netty.nio.group.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 8889;

    public GroupChatServer() {
        try {
            //得到选择器
            this.selector = Selector.open();
            //设置ServerSocketChannel
            this.listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞
            listenChannel.configureBlocking(false);
            //注册到Selector上
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听方法
     */
    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                //如果count大于0，说明有事件需要处理
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            //如果是连接事件，进行连接处理
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            //注册到Selector上
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            //输出提示信息
                            System.out.println(socketChannel.getRemoteAddress() + " login");
                        }
                        if (key.isReadable()) {
                            //通道发生read事件
                            handleReadKey(key);
                        }
                        iterator.remove();
                    }
                } else {
                    System.out.println("Waiting...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleReadKey(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                //读取到数据
                String message = new String(buffer.array());
                //输出消息
                System.out.println("from client:" + message);
                //向其他客户端转发消息
                sendInfoToOterClients(message, channel);
            }

        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + " logout!");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void sendInfoToOterClients(String message, SocketChannel self) throws Exception {
        System.out.println("Server redirect message");
        //遍历所有注册到selector上的SocketChannel，并排除自己
        for (SelectionKey key : selector.keys()) {
            SelectableChannel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel sc = (SocketChannel) targetChannel;
                //将message存储到buffer
                ByteBuffer wrap = ByteBuffer.wrap(message.getBytes());
                //将buffer数据写入通道
                sc.write(wrap);
            }
        }
    }


    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
