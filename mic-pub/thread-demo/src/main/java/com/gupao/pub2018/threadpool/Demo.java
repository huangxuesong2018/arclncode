package com.gupao.pub2018.threadpool;

import java.util.concurrent.*;

/**
 * @author HXS
 * @copyright
 * @since 2019-03-01
 */
public class Demo {
    public static void main(String[] args) {

        /*
        Executors.newFixedThreadPool(2);
        new ThreadPoolExecutor(2, 2,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        Executors.newSingleThreadExecutor();
        new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        Executors.newCachedThreadPool();
        new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        */

        Executor pool = Executors.newFixedThreadPool(2);
        for (int i = 1; i < 10; i++){
            pool.execute(new RunThread());
        }
    }
}
