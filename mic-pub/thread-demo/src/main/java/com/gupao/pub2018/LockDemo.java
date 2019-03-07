package com.gupao.pub2018;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-27
 */
public class LockDemo {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int i = 0;
    public void inc(){
        lock.lock();
        System.out.println("lock--------------"+Thread.currentThread().getName());
        try {
            /**
             * 把当前线程
             */
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        lock.unlock();
        System.out.println("unlock--------------"+Thread.currentThread().getName());
    }


    public static void main(String[] args) throws InterruptedException {
        LockDemo a = new LockDemo();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i< 40; i ++){
            Thread t = new Thread(()->{
                a.inc();
            });
            list.add(t) ;
        }
        for (Thread t: list) {
            t.start();
        }
        for (Thread t: list) {
            t.join();
        }
        System.out.println(LockDemo.i);
    }
}
