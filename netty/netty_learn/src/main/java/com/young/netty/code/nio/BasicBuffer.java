package com.young.netty.code.nio;


import java.nio.IntBuffer;

//缓冲区的使用
public class BasicBuffer {

    public static void main(String[] args) {
        //这个buffer的意思是大小为5，可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }

        //读取数据
        //将buffer读写转换
        intBuffer.flip();

        //是否存有内容
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

    }
}
