package com.gupao.edu.vip.nio.haveatry;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 *  拷备
 * @author HXS
 * @copyright
 * @since 2019-04-17
 */
public class TransferDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("E:\\from.txt","rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("E:\\to.txt","rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        //toChannel.transferFrom(fromChannel,position,count);
        fromChannel.transferTo(position,count,toChannel);
    }

}
