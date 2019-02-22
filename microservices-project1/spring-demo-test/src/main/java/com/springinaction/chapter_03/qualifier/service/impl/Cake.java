package com.springinaction.chapter_03.qualifier.service.impl;

import com.springinaction.chapter_03.qualifier.service.Dessert;
import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@Component
public class Cake implements Dessert {
    @Override
    public void name() {
        System.out.println("cake");
    }
}
