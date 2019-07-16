package com.gupao.edu.vip.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞IO（BIO）
 * Socket编程就是BIO，一个socket连接一个处理线程（这个线程负责这个Socket连接的一系列数据传输操作）。
 * 阻塞的原因在于：操作系统允许的线程数量是有限的，多个socket申请与服务端建立连接时，服务端不能提供相应数量的处理线程，
 * 没有分配到处理线程的连接就会阻塞等待或被拒绝
 * @author HXS
 * @copyright
 * @since 2019-04-12
 */
@Slf4j
public class Server {
    private static int DEFAULT_PORT = 7777;
    private static ServerSocket serverSocket ;

    public static void start() throws IOException{
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port) throws IOException{
        if(serverSocket != null) return;

        try{
            serverSocket = new ServerSocket(port);
            System.out.println("【服务端】-服务端已启动,端口号:"+port);

            while (true){
                System.out.println();
                System.out.println("【服务端】监听........");
                //一个线程管理一个连接
                Socket socket = serverSocket.accept();
                System.out.println("【服务端】-收到一个连接....");
                new Thread(new ServerHandler(socket)).start();
            }
        }finally {
            if (serverSocket != null){
                System.out.println("【服务端】-服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }
}
