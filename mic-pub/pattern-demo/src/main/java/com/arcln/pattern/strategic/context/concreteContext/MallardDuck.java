package com.arcln.pattern.strategic.context.concreteContext;

import com.arcln.pattern.strategic.context.DuckContext;
import com.arcln.pattern.strategic.concreteStrategic.FlyWithWings;
import com.arcln.pattern.strategic.concreteStrategic.Quack;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class MallardDuck extends DuckContext {

    public MallardDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("绿头鸭");
    }
}
