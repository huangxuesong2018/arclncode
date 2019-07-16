package com.arcln.pattern.strategic;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class RubberDuck extends Duck {

    public RubberDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("橡皮鸭");
    }
}
