package com.gupao.edu.vip.nio.haveatry;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-15
 */
public class BufferDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String d = encode("中国人");
        System.out.println(d);
        //解码
        decode(d);
    }

    public static  void decode(String str) throws UnsupportedEncodingException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put(str.getBytes("UTF-8"));
        byteBuffer.flip();

        Charset utf8 = Charset.forName("UTF-8");
        CharBuffer charBuffer = utf8.decode(byteBuffer);

        char[] chars = Arrays.copyOf(charBuffer.array(),charBuffer.limit());
        System.out.println(chars);
    }


    public static String encode(String str){
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.append(str);
        charBuffer.flip();
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = utf8.encode(charBuffer);

        byte[] bytes = Arrays.copyOf(byteBuffer.array(),byteBuffer.limit());
        return Arrays.toString(bytes);
    }
}
