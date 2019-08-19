package com.gupao.edu.vip.bio.talk.client;

import java.io.*;
import java.net.Socket;

/**
 * 向服务端发送消息
 * @author HXS
 * @copyright
 * @since 2019-08-16
 */
public class OutClient implements Runnable {
    private Socket socket;
    private String clientId;

    public OutClient(Socket socket,String clientId) {
        this.socket = socket;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            System.out.println(Thread.currentThread().getName()+"请输入要说的话:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
            String line ;
            try {
                while ((line = br.readLine()) != null){
                    String msg = line;
                    writer.println("["+clientId+"]"+msg);//发送到服务端
                    System.out.println("请输入要说的话:");
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
