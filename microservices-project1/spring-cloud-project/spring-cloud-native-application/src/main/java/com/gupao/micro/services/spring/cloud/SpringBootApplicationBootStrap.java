package com.gupao.micro.services.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class SpringBootApplicationBootStrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setId("小马哥");
        //在“小马哥”上下文注册一个  helloWorld  String类型的Bean
        context.registerBean("helloWorld",String.class,"Hello,World");
        //启动 “小马哥”  上下文
        context.refresh();

        //这里又启动一个上下文件
        new SpringApplicationBuilder(SpringBootApplicationBootStrap.class)
                .parent(context)
                .run(args);
        //SpringApplication.run(SpringBootApplicationBootStrap.class,args);
    }

    @Autowired
    @Qualifier("helloWorld")
    private String message;

    @RequestMapping("")
    public String index(){
        return message;
    }

}
