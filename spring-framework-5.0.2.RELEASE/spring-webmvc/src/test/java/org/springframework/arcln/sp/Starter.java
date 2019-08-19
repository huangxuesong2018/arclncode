package org.springframework.arcln.sp;

import com.springinaction.chapter_01.Knight;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过示例我们对依赖注入进行了一个快速介绍。纵览全书，你将对依赖注入有更多的认识。如果你想了解更多关于依赖注入的信息，我推荐阅
 *  读 Dhanji R. Prasanna 的《 Dependency Injection 》
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
public class Starter {
    /**
     * AnnotationConfigApplicationContext ：从一个或多个基于 Java 的配置类中加载 Spring 应用上下文。
     * AnnotationConfigWebApplicationContext ：从一个或多个基于 Java 的配置类中加载 Spring Web 应用上下文。
     * ClassPathXmlApplicationContext ：从类路径下的一个或多个 XML 配置文件中加载上下文定义，
     * 把应用上下文的定义文件作为类资源。
     * FileSystemXmlapplicationcontext ：从文件系统下的一个或多个 XML 配置文件中加载上下文定义。
     * XmlWebApplicationContext ：从 Web 应用下的一个或多个 XML 配置文件中加载上下文定义
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContextStarter();
    }

    /**
     * 第2种方式
     * 从一个或多个基于 Java 的配置类中加载 Spring Web 应用上下文
     */
    public static void AnnotationConfigApplicationContextStarter(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
