package com.gupao.edu.vip.nio.haveatry;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.Channel;
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
        Channel fromChannel = fromFile.getChannel();


    }
}
