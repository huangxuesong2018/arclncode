package com.arcln.pattern.composite.impl;

import com.arcln.pattern.composite.Quackable;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-23
 */
public class RubberDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
