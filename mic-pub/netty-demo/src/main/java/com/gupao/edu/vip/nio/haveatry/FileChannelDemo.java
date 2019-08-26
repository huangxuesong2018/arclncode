package com.gupao.edu.vip.nio.haveatry;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-16
 */
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {

        RandomAccessFile aFile = new RandomAccessFile("E:\\test.txt","rw");

        //通道
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(20);
        int bytesRead = inChannel.read(buf);

        while (inChannel.read(buf) != -1) {
            buf.flip();
            while(buf.hasRemaining()){//一个字节一个字节读取
                System.out.print((char) buf.get());
            }
            System.out.println();
            buf.clear();
        }
        aFile.close();

    }
}
