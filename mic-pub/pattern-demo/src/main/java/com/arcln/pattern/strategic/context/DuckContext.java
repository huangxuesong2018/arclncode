package com.arcln.pattern.strategic.context;

import com.arcln.pattern.strategic.FlyBehaviorStrategic;
import com.arcln.pattern.strategic.QuackBehaviorStrategic;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public abstract class DuckContext {
    protected QuackBehaviorStrategic quackBehavior;
    protected FlyBehaviorStrategic flyBehavior;

    public void performQuack(){
        quackBehavior.quack();
    }

    public void performFly(){
        flyBehavior.fly();
    }

    public void  swim(){
        System.out.println("I can swim");
    }
    public abstract void display();
}
