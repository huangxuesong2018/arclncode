package com.arcln.pattern.strategic;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-26
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("zi zi zi");
    }
}
