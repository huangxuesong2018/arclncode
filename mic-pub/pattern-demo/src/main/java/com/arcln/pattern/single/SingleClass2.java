package com.arcln.pattern.single;

/**
 * 线程安全，耗性能
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class SingleClass2 {
    private static SingleClass2 instance;
    private SingleClass2(){}

    /**
     * 问题:加入synchronized后性能大大下降，而且只有第一次调用时，才发挥了作用。
     * @return
     */
    public synchronized static SingleClass2 getInstance(){
        if (instance == null){
            instance = new SingleClass2();
        }
        return instance;
    }
}
