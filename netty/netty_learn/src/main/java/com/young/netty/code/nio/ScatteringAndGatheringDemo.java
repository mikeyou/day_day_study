package com.young.netty.code.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering 将数据写入到buffer, 可以采用buffer数组，依次写入[分散]
 * Gathering 从buffer数组中可以依次读(聚合)
 */
public class ScatteringAndGatheringDemo {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 16666);
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8; //假定客户端发送的数据接收8个字节

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead= " + byteRead);
                //打印这个数组里面的每个buffer的position和limit
                Arrays.asList(byteBuffers).stream().map(
                        buffer -> "position= " + buffer.position()
                                + ", limit= " + buffer.limit()
                                + ";"
                ).forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());
            int byteWrite = 0;

            while (byteWrite < messageLength) {
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            Arrays.asList(byteBuffers).forEach(buffer -> buffer.clear());
            System.out.println("byteRead:= " + byteRead + " byteWrite:= "
                    + byteWrite + " messageLength:= " + messageLength);
        }


    }
}
