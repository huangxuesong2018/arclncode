package com.arcln.pattern.proxy.remote.impl;

import com.arcln.pattern.proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-22
 */
public class MyInvocationHandler implements InvocationHandler {
    Subject subject;

    public MyInvocationHandler(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----------请求前"+method.getName());
        Object o = method.invoke(subject,args);
        System.out.println("-----------请求后");
        return null;
    }
}
