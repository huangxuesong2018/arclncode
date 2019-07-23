package com.arcln.pattern.strategic;

import com.arcln.pattern.strategic.context.DuckContext;
import com.arcln.pattern.strategic.context.concreteContext.DecoyDuck;

/**
 * 策略模式
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class Test {
    public static void main(String[] args) {
        DuckContext duck = new DecoyDuck();
        duck.display();
        duck.swim();
        duck.performFly();
        duck.performQuack();
    }
}
