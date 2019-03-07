package com.gupao.pub2018;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author HXS
 * @copyright
 * @since 2019-02-27
 */
public class MyReentrantLock implements Lock {
    private final Sync sync;

    /**
     * Creates an instance of {@code MyReentrantLock}.
     * This is equivalent to using {@code ReentrantLock(false)}.
     */
    public MyReentrantLock() {
        sync = new MyReentrantLock.NonFairSync();
    }

    public MyReentrantLock(boolean fair) {
        sync = fair ? new FairSync() : new NonFairSync();
    }

    abstract static class Sync extends AbstractQueuedSynchronizer{
        abstract void lock();
    }

    /**
     * 非公平锁,默认
     */
    static final class NonFairSync extends Sync {

        /**
         * Performs lock.  Try immediate barge, backing up to normal acquire on failure.
         */
        @Override
        void lock() {
            //获得
            if (compareAndSetState(0, 1))
                setExclusiveOwnerThread(Thread.currentThread());
            else
                acquire(1);
        }

      /*  protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }*/
    }

    static final class FairSync extends Sync{
        @Override
        final void lock() {
            acquire(1);
        }
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
