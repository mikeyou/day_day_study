package com.young.netty.code.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * nio
 */
public class NewIoServer {

    public static void main(String[] args) throws Exception {

        InetSocketAddress address = new InetSocketAddress(16666);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);

        ByteBuffer buffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount = 0;
            while ( -1 != readCount) {
                try {
                    readCount = socketChannel.read(buffer);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                buffer.rewind();  //倒带

            }
        }

    }
}
