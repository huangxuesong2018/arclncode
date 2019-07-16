package com.gupao.edu.vip.bio.talkingroom;

import com.gupao.edu.vip.bio.talkingroom.client.RoomClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-15
 */
public class User1 {
    public static void main(String[] args) {
        System.out.println("请输入要说的话:");
        final String name = "张三:";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
        String line ;
        try {
            while ((line = br.readLine()) != null){
                String msg = line;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-=="+name+msg);
                        //这个地方会一直阻塞
                        RoomClient.talk(name+msg);
                        System.out.println("这个地方会一直阻塞-解除");
                    }
                }).start();

                //  System.out.println(response);
                System.out.println(name+line);
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

    }
}
