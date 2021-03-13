package com.young.netty.code.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(16666);
        serverSocketChannel.socket().bind(inetSocketAddress);

        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将serverSocketChannel注册到selector上，事件为Accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.select(1000) == 0) {
                //没有事件发生
                System.out.println("服务器等待了1s,无连接.....");
                continue;
            }
            //如果返回的大于0,表示获取到关注的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                //
                SelectionKey selectionKey = it.next();
                //根据key看具体事件处理
                if (selectionKey.isAcceptable()) {
                    //有新的客户端链接我, 给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //将该socketChannel注册到selector上
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }

                if (selectionKey.isReadable()) {  //发生OP_READ
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from client : " + new String(byteBuffer.array()));
                }

                //手动从集合中移除当前的selectionKey,防止重复操作
                it.remove();
            }

        }

    }
}
