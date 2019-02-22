package com.springinaction.chapter_03.runtimeinject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
@Configuration
@PropertySource(value = "classpath:chapter_03/app.properties")//声明属性源
public class ExpressiveConfig {
    @Autowired
    private Environment evn;

    /**
     * 在 Spring 中，处理外部值的最简单方式就是声明属性源并通过 Spring 的 Environment 来检索属性
     * @return
     */
    @Bean
    public BlankDisc blankDisc(){
        return new BlankDisc(evn.getProperty("disc.title"),evn.getProperty("disc.artist"));
    }
}
