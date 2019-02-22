package com.springinaction.chapter_03.qualifier.service.impl;

import com.springinaction.chapter_03.qualifier.annotation.Cold;
import com.springinaction.chapter_03.qualifier.annotation.Cookie;
import com.springinaction.chapter_03.qualifier.annotation.Cream;
import com.springinaction.chapter_03.qualifier.service.Dessert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@Component
@Cold
@Cream
public class IceCream implements Dessert {
    @Override
    public void name() {
        System.out.println("iceCream");
    }
}
