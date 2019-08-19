package com.gupao.edu.vip.bio.talk.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 监听连接
 * @author HXS
 * @copyright
 * @since 2019-08-16
 */
public class ServerListener extends Thread{
    private final static int PORT = 23456;
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器启动，端口:"+PORT);
            while (true){
                /**
                 * 这个地方阻塞，直到有新的用户上线了，创建一个新的长连接
                 */
                Socket socket = serverSocket.accept();
                System.out.println("有客户端连接到端口:"+PORT);
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                ChatManager.getChatManager().add(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                    System.out.println("服务器关闭，端口:"+PORT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
