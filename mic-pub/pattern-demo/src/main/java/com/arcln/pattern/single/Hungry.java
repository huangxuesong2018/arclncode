package com.arcln.pattern.single;

/**
 * 饿汉模式 -线程安全
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class Hungry {
    private static Hungry hungry = new Hungry();
    private Hungry(){}
    public static Hungry getInstance(){
        return hungry;
    }
}
