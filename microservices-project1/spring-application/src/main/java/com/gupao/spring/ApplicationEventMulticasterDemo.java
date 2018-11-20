package com.gupao.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

public class ApplicationEventMulticasterDemo {
    public static void main(String[] args) {
        ApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.addApplicationListener(event -> {
            System.out.println("接收到事件："+event);
        });
        multicaster.multicastEvent(new MyEvent("Hello world33"));
        multicaster.multicastEvent(new PayloadApplicationEvent<Object>("2","Hello world"));
    }

    //自定义监听事件
    private static class MyEvent extends ApplicationEvent {

        public MyEvent(Object source) {
            super(source);
        }
    }
}
