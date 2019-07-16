package com.gupao.edu.vip.bio;

import java.util.Random;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-12
 */
public class ServerStart {
    public static void main(String[] args) throws InterruptedException {

        //用一个线程 启动服务端
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Server.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
