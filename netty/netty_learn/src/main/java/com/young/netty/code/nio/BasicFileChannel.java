package com.young.netty.code.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

//Nio中的channel概念
public class BasicFileChannel {

    public static void main(String[] args) throws Exception {
        //writeByFileChannelDemo();
        //readByFileChannelDemo();
        //copyByFileChannelDemo();

        transferDemo();
    }


    //使用transferFrom进行拷贝
    public static void transferDemo() throws Exception {
        FileInputStream fis = new FileInputStream("F:\\picture\\index2.jpg");
        FileOutputStream fos = new FileOutputStream("F:\\picture\\index5.jpg");

        FileChannel sourceChannel = fis.getChannel();
        FileChannel targetChannel = fos.getChannel();

        targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        sourceChannel.close();
        fis.close();
        targetChannel.close();
        fos.close();
    }


    //文件拷贝
    public static void copyByFileChannelDemo() throws Exception {
        FileInputStream fis = new FileInputStream("Netty_READEME.md");
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("F:\\file01.txt");
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true) {
            byteBuffer.clear();
            int read = fisChannel.read(byteBuffer);
            if (read == -1) {
                //读取完毕
                break;
            }
            byteBuffer.flip();
            fosChannel.write(byteBuffer);
        }

        fisChannel.close();
        fis.close();
        fosChannel.close();
        fos.close();
    }

    //读取一段文本
    public static void readByFileChannelDemo() throws Exception {
        File file = new File("F:\\file01.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileChannel = fis.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));
        fis.close();
        fileChannel.close();
    }

    //写一段文本输出
    public static void writeByFileChannelDemo() throws Exception {
        String str = "Hello JAVA \nHello GO";
        FileOutputStream fos = new FileOutputStream("F:\\file01.txt");
        FileChannel fileChannel = fos.getChannel();

        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fos.close();
        fileChannel.close();
    }
}
