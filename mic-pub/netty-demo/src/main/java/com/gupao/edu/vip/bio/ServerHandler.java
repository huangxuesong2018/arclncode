package com.gupao.edu.vip.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-12
 */
@Slf4j
public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression ;
            String result;
            while (true){
                if((expression = in.readLine()) == null) break;
                System.out.println("【服务端.处理】-处理中..."+ expression);
                result = Calculator.cal(expression);
                System.out.println("【服务端.处理】-响应客户端");
                out.println(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
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
