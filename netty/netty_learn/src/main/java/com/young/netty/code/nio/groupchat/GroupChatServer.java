package com.young.netty.code.nio.groupchat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenerChannel;

    private static final int PORT = 16666;

    public GroupChatServer() {
        try {
            this.selector = Selector.open();
            this.listenerChannel = ServerSocketChannel.open();
            listenerChannel.socket().bind(new InetSocketAddress(PORT));
            listenerChannel.configureBlocking(false);
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //监听代码
    public void listen() {
        try {
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    //有事件处理
                    Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = this.listenerChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(this.selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + "上线....");
                        }
                        if (key.isReadable()) {
                            //处理读的方法
                            readData(key);
                        }
                        it.remove();
                    }
                } else{
                    System.out.println("等待....");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void readData(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);
            if (count > 0) {
                //
               String msg = new String(buffer.array());
               System.out.println("from 客户端: " + msg);
               //向其他客户端转发消息
                sendInfoToOtherClients(msg, socketChannel);
            }
        } catch (Exception e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + "已经离线了....");
                key.cancel();
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //转发消息
    public void sendInfoToOtherClients(String msg, SocketChannel self) {
        System.out.println("服务器转发消息开始.....");
        try {
            for (SelectionKey key : this.selector.keys()) {
                Channel targetChannel = key.channel();
                //排除自己，找寻其他客户端
                if (targetChannel instanceof SocketChannel
                        && targetChannel != self) {
                    SocketChannel target = (SocketChannel) targetChannel;
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    //
                    target.write(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
