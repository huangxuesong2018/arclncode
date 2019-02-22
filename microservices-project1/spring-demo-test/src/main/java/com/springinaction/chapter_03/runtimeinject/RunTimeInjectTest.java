package com.springinaction.chapter_03.runtimeinject;

import com.sun.javafx.runtime.SystemProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */

public class RunTimeInjectTest {
    /**
     * 1.直接从 Environment 中检索属性
     * 2.属性占位符的使用
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = ExpressiveConfig.class)
    public static class EnvironmentTest{

        @Autowired
        private BlankDisc blankDisc;
        @Test
        public void t(){
            System.out.println(blankDisc.toString());
        }
    }


    /**
     * Spring 表达式
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = ExpressiveConfig.class)
    public static class SpELTest{

        /**
         * T() 表达式会将 java.lang.System 视为 Java 中对应的类型，因此可以调用
         * T() 运算符的真正价值在于它能够访问目标类型的静态方法和常量
         * 其 static 修饰的 currentTimeMillis() 方法
        */
        @Value("#{T(System).currentTimeMillis()}")
        private String num;

        //SpEL 表达式也可以引用其他的 bean 或其他 bean 的属性。例如，如下的表达式会计算得到 ID 为 blankDisc 的 bean 的 author 属性
        @Value("#{blankDisc.author}")
        private String author;

        //引用 bean
        @Value("#{blankDisc}")
        private BlankDisc disc;
        @Test
        public void t(){
            System.out.println(num);
            System.out.println(author);
            System.out.println(disc);
        }
    }


    /**
     * 1.直接从 Environment 中检索属性
     * 2.属性占位符的使用
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = AutoScanConfig.class)
    public static class AutoScanTest{

        @Autowired
        private OtherDisc otherDisc;
        @Test
        public void t(){
            System.out.println(otherDisc.toString());
        }
    }


}
