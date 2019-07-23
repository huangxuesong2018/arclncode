package com.arcln.pattern.strategic.concreteStrategic;

import com.arcln.pattern.strategic.FlyBehaviorStrategic;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class FlyNoWay implements FlyBehaviorStrategic {
    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}
