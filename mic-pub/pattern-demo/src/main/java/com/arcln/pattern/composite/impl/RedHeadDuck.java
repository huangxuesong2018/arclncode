package com.arcln.pattern.composite.impl;

import com.arcln.pattern.composite.Quackable;

/**
 * 红头鸭
 * @author HXS
 * @copyright
 * @since 2019-07-23
 */
public class RedHeadDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("quack");
    }
}
