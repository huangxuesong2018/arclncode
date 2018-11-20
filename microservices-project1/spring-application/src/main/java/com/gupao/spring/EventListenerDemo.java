package com.gupao.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;

public class EventListenerDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        //添加事件监听器
/*        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.err.println("监听事件:"+event);
            }
        });*/


        //启动spring 应用上下文
        context.refresh();
        context.addApplicationListener(new CloseListener());
        //应用上下文发布事件
        context.publishEvent("HelloWorld");//发布一个HelloWorld内容事件

        context.publishEvent(new MyEvent("HelloWorld 2018"));

        //关
        context.close();
    }

    private static class CloseListener implements ApplicationListener<ContextClosedEvent>{
        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            System.err.println("关闭上下文"+event);
        }
    }

    private static class refreshListener implements ApplicationListener<ContextRefreshedEvent>{
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.err.println("启动上下文"+event);
        }
    }

    //自定义监听事件
    private static class MyEvent extends ApplicationEvent{

        public MyEvent(Object source) {
            super(source);
        }
    }
}
