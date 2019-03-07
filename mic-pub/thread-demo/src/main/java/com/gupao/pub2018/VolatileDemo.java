package com.gupao.pub2018;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {

    public void demo1(){
        synchronized (VolatileDemo.class){
            int a = 4;
            a++;
        }
    }
    public void demo2(){
        synchronized (this){
            int a = 4;
            a++;
        }
    }

    AtomicInteger ato = new AtomicInteger();
    public void cas(){
        ato.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {

    }
}
