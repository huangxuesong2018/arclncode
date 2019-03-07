package com.gupao.pub2018;
public class ThreadDemo {
    /* 加上 volatile 后，可以看到
       0x0000000002c10acd: lock add dword ptr [rsp],0h  ;*putstatic instance
                      ; - com.gupao.pub2018.ThreadDemo::getInstance@13 (line 7)
    */ private static volatile ThreadDemo instance = null;

    public static ThreadDemo getInstance(){
        if(instance == null){
            instance = new ThreadDemo();
        }
        return instance;
    }

    public static void main(String[] args) {

    }
}
