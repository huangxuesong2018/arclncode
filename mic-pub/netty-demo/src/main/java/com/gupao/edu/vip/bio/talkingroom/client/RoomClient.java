package com.gupao.edu.vip.bio.talkingroom.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-12
 */
public class RoomClient{
    private static int DEFAULT_PORT = 8888;
    private static String DEFAULT_IP = "127.0.0.1";
    public static void talk(String expression){
         talk(DEFAULT_PORT,expression);
    }
    public static void talk(int defaultPort, String expression){
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            //准备连接进入聊天室
            Socket socket = new Socket(DEFAULT_IP,DEFAULT_PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            //System.out.println("发送消息:"+expression);
            writer.println(expression);
            String line;
            //这个地方一直阻塞..直到有请求内容
            while ((line = reader.readLine()) != null){
                //System.out.println("------"+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
