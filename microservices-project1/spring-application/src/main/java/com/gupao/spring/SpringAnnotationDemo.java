package com.gupao.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAnnotationDemo {
    public static void main(String[] args) {
        //xml 配置文件驱动 ClassPathXmlApplicationContext
        //Annotation驱动
        //找BeanDefinition
        //@Bean @Configuration
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationDemo.class);
        context.refresh();//上下文启动
        System.out.println(context.getBean(SpringAnnotationDemo.class));
    }
}
