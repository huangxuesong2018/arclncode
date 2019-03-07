package com.gupao.pub2018.interrupt;

import com.gupao.pub2018.ThreadDemo;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-28
 */
public class Demo implements Runnable{

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){//-> currentThread().isInterrupted(false)
            System.out.println("["+Thread.currentThread().getName()+"]线程正在运行");
        }
        System.out.println("["+Thread.currentThread().getName()+"]被中断了");
       // System.out.println(Thread.interrupted());                  //-> currentThread().isInterrupted(true);

    }

    public static void main(String[] args) {
        Demo d = new Demo();
        Thread t = new Thread(d);
        t.start();
        System.out.println("线程:"+t.getName()+"，中断状态："+t.isInterrupted());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();   //中断当前线程
        System.out.println("中断当前线程"+t.getName());
        System.out.println("线程:"+t.getName()+"，中断状态："+t.isInterrupted());
        Thread.interrupted();
        System.out.println("["+Thread.currentThread().getName()+"]"+Thread.currentThread().isInterrupted());
    }

}
