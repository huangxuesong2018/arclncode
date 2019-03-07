package com.gupao.pub2018.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-28
 */
public class ThreadNotify implements Runnable {
    private Lock lock;
    private Condition condition;

    public ThreadNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            System.out.println("["+Thread.currentThread().getName()+"]lock....");
            lock.lock();
            System.out.println("["+Thread.currentThread().getName()+"]signal....");
            /**
             * 把等待对列【{@link java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject}】中的firstWaiter节点，
             * 重新加到AQS 对列的tail{@link java.util.concurrent.locks.AbstractQueuedSynchronizer#tail}节点
             */
            condition.signal();
        }finally {
            System.out.println("["+Thread.currentThread().getName()+"]unlock....");
            lock.unlock();
        }
    }
}

