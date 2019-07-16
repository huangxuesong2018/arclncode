package com.arcln.pattern.decorator.io;

import java.io.*;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-01
 */
public class Test {
    public static void main(String[] args) throws IOException {

        LowerCaseInputStream inputStream = new LowerCaseInputStream(
                new BufferedInputStream(
                        new FileInputStream(
                                new File("E:/source/temp/t.txt"))
        ));

        byte[] buf = new byte[1024];
        int r = 0;
        while ((r = inputStream.read(buf)) != -1){
            System.out.println("-"+new String(buf,0,r));
        }
        inputStream.close();
    }
}
