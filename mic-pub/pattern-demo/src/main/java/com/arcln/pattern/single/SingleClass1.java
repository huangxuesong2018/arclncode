package com.arcln.pattern.single;

/**
 * 线程不安全
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class SingleClass1 {
    private static SingleClass1 instance;
    private SingleClass1(){}
    public static SingleClass1 getInstance(){
        /**
         * 多线程的时候，可以产生多个实例出来
         * 继续看 {@link SingleClass2}
         */
        if (instance == null){
            instance = new SingleClass1();
        }
        return instance;
    }


}
