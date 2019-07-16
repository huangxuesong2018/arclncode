package com.gupao.edu.vip.bio;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class Client {
    private static int DEFAULT_PORT = 7777;
    private static String DEFAULT_IP = "127.0.0.1";

    public static void send(String expression){
        send(DEFAULT_PORT,expression);
    }

    public static void send(int defaultPort, String expression) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            System.out.println("【客户端】-准备连接服务端");
            socket = new Socket(DEFAULT_IP, DEFAULT_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("【客户端】-连接上服务端-发送消息到服务端");
            out.println(expression);
            System.out.println("【客户端】-服务端响应"+in.readLine());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                out.close();
                out = null;
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
