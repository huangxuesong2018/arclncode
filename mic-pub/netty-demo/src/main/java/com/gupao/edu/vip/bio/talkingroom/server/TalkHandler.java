package com.gupao.edu.vip.bio.talkingroom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-12
 */
public class TalkHandler implements Runnable {
    //所有Socket会话
    private Socket socket;
    public TalkHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run(){
        distributionMessage(socket);
    }

    /**
     * 分发消息
     * @param socket
     */
    public void distributionMessage(Socket socket){
        BufferedReader reader = null;
        PrintWriter writer = null;
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            String readerText;
            while ((readerText = reader.readLine()) != null){

                writer.println(readerText);
                System.out.println(readerText);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null){
                writer.close();
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
