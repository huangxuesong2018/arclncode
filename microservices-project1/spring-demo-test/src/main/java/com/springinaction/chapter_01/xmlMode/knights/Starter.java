package com.springinaction.chapter_01.xmlMode.knights;


import com.springinaction.chapter_01.Knight;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 通过示例我们对依赖注入进行了一个快速介绍。纵览全书，你将对依赖注入有更多的认识。如果你想了解更多关于依赖注入的信息，我推荐阅
 *  读 Dhanji R. Prasanna 的《 Dependency Injection 》
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
@EnableScheduling
public class Starter {
    public static void main(String[] args) {
        /**
         * AnnotationConfigApplicationContext ：从一个或多个基于 Java 的配置类中加载 Spring 应用上下文。
         * AnnotationConfigWebApplicationContext ：从一个或多个基于 Java 的配置类中加载 Spring Web 应用上下文。
         * ClassPathXmlApplicationContext ：从类路径下的一个或多个 XML 配置文件中加载上下文定义，
         * 把应用上下文的定义文件作为类资源。
         * FileSystemXmlapplicationcontext ：从文件系统下的一个或多个 XML 配置文件中加载上下文定义。
         * XmlWebApplicationContext ：从 Web 应用下的一个或多个 XML 配置文件中加载上下文定义
         */
        ClassPathXmlApplicationContextStarter();
    }

    /**
     * 第1种方式
     * 从类路径下的一个或多个 XML 配置文件中加载上下文定义，把应用上下文的定义文件作为类资源
     *
     */
    public static void ClassPathXmlApplicationContextStarter(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter_01/knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }



    /**
     * 第3种方式
     * 从文件系统下的一个或多个 XML 配置文件中加载上下文定义。
     */
    public static void FileSystemXmlApplicationContextStarter(){
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(
                "F:\\arcln-code\\microservices-project1\\spring-demo-test\\src\\main\\resources\\chapter_01\\knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }


}
