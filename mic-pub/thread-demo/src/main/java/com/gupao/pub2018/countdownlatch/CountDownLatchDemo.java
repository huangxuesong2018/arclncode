package com.gupao.pub2018.countdownlatch;

import java.util.concurrent.*;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-01
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(()->{
            System.out.println("11");
           // countDownLatch.countDown();
        }).start();
        new Thread(()->{
            System.out.println("22");
           // countDownLatch.countDown();
        }).start();
        new Thread(()->{
            System.out.println("33");
           // countDownLatch.countDown();
        }).start();
        /**
         *  return (getState() == 0) ? 1 : -1;  ->  -1
         */
        countDownLatch.await();
        System.out.println("over");

    }
}
