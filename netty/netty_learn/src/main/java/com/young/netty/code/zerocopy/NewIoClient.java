package com.young.netty.code.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIoClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 16666));
        String fileName = "E:\\book\\pdf\\Netty权威指南.pdf";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();
        long position = 0;
        long total = 0;
        long size = fileChannel.size();
        while (position < size) {
            long transfer = fileChannel.transferTo(position, fileChannel.size(), socketChannel);
            position += transfer;
            total += transfer;
        }

        System.out.println("发送的总字节数: " + total + "耗时: " + (System.currentTimeMillis() - startTime));

        fileChannel.close();
        socketChannel.close();

    }
}
