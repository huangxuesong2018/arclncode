package com.arcln.pattern.strategic;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("quack quack quack");
    }
}
