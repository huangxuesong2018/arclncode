package com.springinaction.chapter_04;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-22
 */
public class DemoTest {

    /**
     * 使用 JavaConfig  {@link ConcertConfig}
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = ConcertConfig.class)
    public static class AopTest{
        @Autowired
        private Performance performance;

        @Test
        public void test(){
            performance.perform();
        }
    }


    /**
     * 使用 XMLConfig  classpath:chapter_04/concert-config.xml
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = "classpath:chapter_04/concert-config.xml")
    public static class AopXMLTest{
        @Autowired
        private Performance performance;

        @Test
        public void test(){
            performance.perform();
        }
        @Test
        public void test2(){
            performance.perform("ai oe");
        }
    }
}
