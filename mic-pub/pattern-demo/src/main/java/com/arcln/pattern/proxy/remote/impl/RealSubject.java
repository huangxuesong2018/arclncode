package com.arcln.pattern.proxy.remote.impl;

import com.arcln.pattern.proxy.Subject;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-22
 */
public class RealSubject implements Subject {
    @Override
    public String request(String msg) {
        System.out.println("request()");
        return "hello,"+msg;
    }

    @Override
    public void sayHello() {
        System.out.println("sayHello()");
    }
}
