package com.arcln.pattern.proxy;

import com.arcln.pattern.proxy.remote.impl.MyInvocationHandler;
import com.arcln.pattern.proxy.remote.impl.RealSubject;

import java.lang.reflect.Proxy;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-22
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Subject proxy = (Subject) Proxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                new MyInvocationHandler(subject));
        String rs = proxy.request("hxs");
        proxy.sayHello();
    }
}
