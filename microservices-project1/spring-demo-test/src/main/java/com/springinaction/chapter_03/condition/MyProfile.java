package com.springinaction.chapter_03.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * {@link org.springframework.context.annotation.Profile} 源码分析
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MyProfileCondition.class)
public @interface MyProfile {
    /**
     * 搞简单点，只能设置一个
     * @return
     */
    String value();

    String other();
}
