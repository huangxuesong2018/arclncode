package com.gupao.pub2018.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-01
 */
public class Starter {
    public static void main(String[] args) throws InterruptedException {
        //重入锁
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread(new ThreadWait(lock,condition));
        Thread t2 = new Thread(new ThreadNotify(lock,condition));
        t1.start();
        Thread.sleep(100);
        t2.start();

    }
}
