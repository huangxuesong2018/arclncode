package com.springinaction.chapter_03.condition;

import org.springframework.context.annotation.*;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@Configuration
@PropertySource(value = "classpath:condition.properties")
public class BeanConfig {

/*    @Bean
    @Conditional(value = MagicBeanCondition.class)
    public MagicBean magicBean(){
        return new MagicBean();
    }*/

    @Bean
    @MyProfile(value = "A",other = "1")
    public MagicBean magicBeanA(){
        return new MagicBean("历害的");
    }

    @Bean
    @MyProfile(value = "B",other = "2")
    public MagicBean magicBeanB(){
        return new MagicBean("一般的");
    }

}
