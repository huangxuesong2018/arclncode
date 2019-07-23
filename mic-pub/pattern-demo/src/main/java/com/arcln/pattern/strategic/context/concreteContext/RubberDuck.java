package com.arcln.pattern.strategic.context.concreteContext;

import com.arcln.pattern.strategic.context.DuckContext;
import com.arcln.pattern.strategic.concreteStrategic.Squeak;
import com.arcln.pattern.strategic.concreteStrategic.FlyNoWay;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class RubberDuck extends DuckContext {

    public RubberDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("橡皮鸭");
    }
}
