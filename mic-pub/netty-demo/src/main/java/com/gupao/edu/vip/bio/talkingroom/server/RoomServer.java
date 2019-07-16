package com.gupao.edu.vip.bio.talkingroom.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-12
 */
public class RoomServer {
    private static int DEFAULT_PORT = 8888;
    private static ServerSocket serverSocket ;

    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    /**
     * 打开聊天室
     * @param port
     * @throws IOException
     */
    public synchronized static void start(int port) throws IOException {
        if(serverSocket != null) return;

        try{
            serverSocket = new ServerSocket(port);
            System.out.println("【聊天室】-聊天室已启动,端口号:"+port);

            while (true){
                System.out.println();
                System.out.println("【聊天室】监听........");
                Socket socket = serverSocket.accept();
                System.out.println("【聊天室】-收到一个连接....");
                new Thread(new TalkHandler(socket)).start();
            }
        }finally {
            if (serverSocket != null){
                System.out.println("【聊天室】-聊天室已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }

}
