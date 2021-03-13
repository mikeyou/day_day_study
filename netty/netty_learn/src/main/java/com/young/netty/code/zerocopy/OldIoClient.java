package com.young.netty.code.zerocopy;

import java.io.*;
import java.net.Socket;

public class OldIoClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 16666);
        String fileName = "E:\\book\\pdf\\Netty权威指南.pdf";
        InputStream is = new FileInputStream(fileName);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();
        while ((readCount = is.read(buffer)) != -1) {
            total += readCount;
            dos.write(buffer);
            dos.flush();
        }
        System.out.println("发送字节数: " + total + "耗时: " + (System.currentTimeMillis() - startTime));

        dos.close();
        socket.close();
        is.close();

    }
}
