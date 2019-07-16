package com.arcln.pattern.strategic;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("绿头鸭");
    }
}
