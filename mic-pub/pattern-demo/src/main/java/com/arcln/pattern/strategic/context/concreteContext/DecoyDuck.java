package com.arcln.pattern.strategic.context.concreteContext;

import com.arcln.pattern.strategic.context.DuckContext;
import com.arcln.pattern.strategic.concreteStrategic.FlyNoWay;
import com.arcln.pattern.strategic.concreteStrategic.MuteQuack;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class DecoyDuck extends DuckContext {
    public DecoyDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("诱饵鸭");
    }
}
