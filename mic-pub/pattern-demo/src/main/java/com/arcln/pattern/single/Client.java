package com.arcln.pattern.single;

import java.util.concurrent.*;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class Client {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i< 10; i++){
            Thread t = new Thread(()->{
                System.out.println(SingleClass3.getInstance());
            });
            executor.execute(t);
        }
    }

}
