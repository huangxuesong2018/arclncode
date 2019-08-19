package com.gupao.edu.vip.bio.talk.server;


import java.io.*;
import java.net.Socket;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-16
 */
public class ChatSocket extends Thread {
    private Socket socket;

    public ChatSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"UTF-8"));
            try{
                String text = null;
                /**
                 * 一直阻塞,监听每个用户的输入
                 */
                while ((text = reader.readLine()) != null){
                    ChatManager.getChatManager().publish(this,text);
                }
            } catch (IOException e) {
                System.out.println(socket+"下线了");
                ChatManager.getChatManager().remove(this);
                //e.printStackTrace();
            }finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("IOException>1");
            e.printStackTrace();
        }
    }

    /**
     * 服务端给客户端发送响应消息
     * @param msg
     */
    public void out(String msg) {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            writer.println(msg);
           // writer.close();//这个地方关掉会把 socket也关掉
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
