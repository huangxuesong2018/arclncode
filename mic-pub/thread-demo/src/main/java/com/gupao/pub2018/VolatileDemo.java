package com.gupao.pub2018;

public class VolatileDemo {
    private static int a = 0,b = 0;
    private static int x = 0,y = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            a = 1;//(1)
            x = b;//(2)
        });
        Thread t2 = new Thread(() -> {
            b = 1;//(3)
            y = a;//(4)
        });
        t1.start();
        t2.start();
        t2.join();
        t1.join();
        System.out.println("x=" + x + ",y=" + y);
        //x=1,y=0,
        //x=0,y=1  (2)->(3)   (1)
        //  ->(4)
    }
}
