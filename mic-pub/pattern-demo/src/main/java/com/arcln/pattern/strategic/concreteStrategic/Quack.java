package com.arcln.pattern.strategic.concreteStrategic;

import com.arcln.pattern.strategic.QuackBehaviorStrategic;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class Quack implements QuackBehaviorStrategic {
    @Override
    public void quack() {
        System.out.println("quack quack quack");
    }
}
