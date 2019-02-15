package com.gupao.micro.service.spring.cloud.client.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;

/**
 * 事件三步曲(1->定义事件, 2->发布事件,3->监听事件)
 * @author HXS
 * @copyright
 * @since 2019-01-28
 */
public class SpringAnnotationDriveEvent {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationDriveEvent.class);
        context.refresh();
        //发布一个自定义事件
        context.publishEvent(new MyApplicationEvent("Hello World"));

        context.close();
    }

    /**
     * 自定义Spring 事件
     */
    public static class MyApplicationEvent extends ApplicationEvent{
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }

    /**
     * 只监听 MyApplicationEvent类型的事件
     * @param event
     */
    @EventListener
    public void onMessage(MyApplicationEvent event){
        System.err.println("MyApplicationEvent:监听"+event);
    }

    /**
     * 监听 ApplicationEvent类型的事件
     * @param event
     */
    @EventListener
    public void onMessage(Object event){
        System.err.println("Object:监听"+event);
    }

}
