package com.arcln.pattern.adapter.impl;

import com.arcln.pattern.adapter.Duck;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-15
 */
public class MallardDuck implements Duck {
    @Override
    public void fly() {
        System.out.println("Flying...");
    }

    @Override
    public void quack() {
        System.out.println("quack quack...");
    }
}
