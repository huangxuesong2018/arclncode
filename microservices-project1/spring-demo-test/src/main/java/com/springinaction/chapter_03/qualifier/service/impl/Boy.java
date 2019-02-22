package com.springinaction.chapter_03.qualifier.service.impl;

import com.springinaction.chapter_03.qualifier.annotation.Cold;
import com.springinaction.chapter_03.qualifier.annotation.Cookie;
import com.springinaction.chapter_03.qualifier.service.Dessert;
import com.springinaction.chapter_03.qualifier.service.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@Component
public class Boy implements Person {
    private Dessert dessert;

    /**
     * 在注入点，我们使用必要的限定符注解进行任意组合，从而将可选范围缩小到只有一个 bean 满足需求
     * @param dessert
     */
    @Autowired
    @Cold
    @Cookie
    private void setDessert(Dessert dessert){
        this.dessert = dessert;
    }

    @Override
    public void eat() {
        dessert.name();
    }
}
