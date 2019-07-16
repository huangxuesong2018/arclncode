package com.arcln.pattern.strategic;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}
