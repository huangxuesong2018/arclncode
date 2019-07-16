package com.arcln.pattern.strategic;

/**
 * 策略模式
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class Test {
    public static void main(String[] args) {
        Duck duck = new DecoyDuck();
        duck.display();
        duck.swim();
        duck.performFly();
        duck.performQuack();
    }
}
