package com.gupao.micro.service.spring.cloud.client.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-28
 */
public class SpringEvent {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener(e -> {
            System.err.println("事件触发:"+e.getClass().getSimpleName());
        });
        context.refresh();
        System.err.println("1"+context.isActive());
        context.start();
        System.err.println("2"+context.isActive());
        context.stop();
        System.err.println("3"+context.isActive());
        context.close();
        System.err.println("4"+context.isActive());
    }
}
