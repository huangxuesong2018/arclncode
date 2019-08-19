package com.gupao.edu.vip.bio.talk.client;
import java.io.*;
import java.net.Socket;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-16
 */
public class ClientManager {
    private final static int DEFAULT_PORT = 23456;
    private static String DEFAULT_IP = "127.0.0.1";
    public static void talk(String appId){
        talk(DEFAULT_PORT,appId);
    }
    public static void talk(int defaultPort,String appId){
        try {
            Socket socket = new Socket(DEFAULT_IP,DEFAULT_PORT);
            new Thread(new OutClient(socket,appId)).start();
            new Thread(new InClient(socket)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
