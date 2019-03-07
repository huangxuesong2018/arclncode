package com.gupao.pub2018.threadpool;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-01
 */
public class
  RunThread implements Runnable {
    @Override
    public void run() {
        System.out.println("["+Thread.currentThread().getName()+"]");
    }
}
