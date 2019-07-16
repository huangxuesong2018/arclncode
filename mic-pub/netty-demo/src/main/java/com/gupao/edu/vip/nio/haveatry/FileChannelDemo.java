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

        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);

        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

    }
}
