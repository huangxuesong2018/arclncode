package com.springinaction.chapter_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 音乐会的配置
 * 声明式注入Spring bean
 * @author HXS
 * @copyright
 * @since 2019-02-22
 */
@Configuration
@EnableAspectJAutoProxy //采用注解 启用自动代理功能
@ComponentScan //启用自动扫描
public class ConcertConfig {

/*    @Bean
    public Audience audience(){
        return new Audience();
    }

    @Bean
    public Performance performance(){
        return new PerformanceImpl();
    }*/
}
