package com.gupao.microservicesproject1;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
@EnableAutoConfiguration
public class SpringBootEventDemo {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootEventDemo.class)
                .listeners(event -> {
                    System.err.println("监听到事件："+event);
                })//添加监听器
                .run(args)//运行
                .close();
    }
}
