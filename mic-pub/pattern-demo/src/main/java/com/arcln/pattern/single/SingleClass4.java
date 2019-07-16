package com.arcln.pattern.single;

/**
 * 双重检查锁
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class SingleClass4 {
    //加入 volatile 关键字 解决可见性
    private volatile static SingleClass4 instance;
    private SingleClass4(){}

    public static SingleClass4 getInstance(){
        if (instance == null){
            synchronized (SingleClass4.class){
                if (instance == null){
                    instance = new SingleClass4();
                }
            }
        }
        return instance;
    }
}
