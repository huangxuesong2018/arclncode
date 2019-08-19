package com.gupao.edu.vip.nio.haveatry;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-17
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        //Selector的创建
        Selector selector = Selector.open();
        RandomAccessFile fromFile = new RandomAccessFile("E:\\from.txt","rw");
        FileChannel fromChannel = fromFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = fromChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = fromChannel.read(buf);
        }
        fromFile.close();
    }
}
