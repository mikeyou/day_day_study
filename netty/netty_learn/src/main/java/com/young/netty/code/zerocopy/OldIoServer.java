package com.young.netty.code.zerocopy;


import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

//传统io的copy
public class OldIoServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(16666);

        while(true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] dataArray = new byte[4096];
                while (true) {
                    int readCount = dataInputStream.read(dataArray, 0, dataArray.length);
                    if (-1 == readCount) {
                        break;
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
