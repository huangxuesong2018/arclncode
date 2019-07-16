package com.gupao.edu.vip.bio;

import java.util.Random;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-12
 */
public class ClientStart {
    public static void main(String[] args) {
        final char[] op = {'+','-','*','/'};
        final Random random = new Random(System.currentTimeMillis());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println();
                    System.out.println("【TEST】发起请求********************************************");
                    String expression = random.nextInt(10) + "" + op[random.nextInt(4)];
                    Client.send(expression);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
