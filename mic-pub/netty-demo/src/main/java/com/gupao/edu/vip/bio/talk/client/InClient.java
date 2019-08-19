package com.gupao.edu.vip.bio.talk.client;

import java.io.*;
import java.net.Socket;

/**
 * 接收服务端消息
 * @author HXS
 * @copyright
 * @since 2019-08-16
 */
public class InClient implements Runnable {
    private Socket socket;

    public InClient(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = null;
            while ((message = reader.readLine()) != null){
                System.out.println(threadName+"----"+message);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
