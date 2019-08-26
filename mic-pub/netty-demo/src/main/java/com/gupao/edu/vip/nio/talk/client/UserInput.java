package com.gupao.edu.vip.nio.talk.client;

import com.gupao.edu.vip.nio.talk.entity.Message;
import io.netty.channel.Channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class UserInput implements Runnable{
    private SendChannel sendChannel;
    private String userName;

    private void sendMessage(){
        String msg;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("请输入用户名:");
            userName = reader.readLine();
            Message message = new Message(userName);
            System.out.println("请输入消息:");
            while ((msg = reader.readLine()) != null ){
                message.setMessage(msg);
                sendChannel.getChannel().writeAndFlush(message);
                System.out.println("请输入消息:");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        SendChannel channel =  new SendChannel("127.0.0.1",8080);
        channel.start();
        this.sendChannel = channel;
        sendMessage();
    }
}
