package com.gupao.pub2018.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-28
 */
public class ThreadWait implements Runnable {
    private Lock lock;
    private Condition condition;

    public ThreadWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            System.out.println("["+Thread.currentThread().getName()+"]lock....");
            lock.lock();
            System.out.println("["+Thread.currentThread().getName()+"]await....");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("["+Thread.currentThread().getName()+"]unlock....");
            lock.unlock();
        }
    }
}
