package com.young.netty.code.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//BIO_demo
public class BIOServer {

    public static void main(String[] args) throws IOException {

        //线程池方式
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        ServerSocket serverSocket = new ServerSocket(16666);
        System.out.println("服务器启动.....");
        while (true) {
            //监听等待客户端连接
            final Socket accept = serverSocket.accept();
            System.out.println("有客户端连接成功.....");

            executorService.execute(new Runnable() {
                public void run() {
                    handler(accept);
                }
            });

        }
    }

    //编写handler方法，和客户端通讯
    public static void handler(Socket socket)  {

        try {
            byte[] bytes = new byte[1024];
            InputStream is = socket.getInputStream();
            while (true) {
                int read = is.read(bytes);
                if (read != -1) {
                    System.out.println(Thread.currentThread().getName() + "客户端发送的数据: " + new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("客户端关闭连接......");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
