package com.springinaction.chapter_01;


import com.springinaction.chapter_01.knights.Knight;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过示例我们对依赖注入进行了一个快速介绍。纵览全书，你将对依赖注入有更多的认识。如果你想了解更多关于依赖注入的信息，我推荐阅
 *  读 Dhanji R. Prasanna 的《 Dependency Injection 》
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
public class Starter {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knight.xml");

        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
