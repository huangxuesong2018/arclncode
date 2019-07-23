package com.arcln.pattern.composite.impl;

import com.arcln.pattern.composite.Quackable;

/**
 * 绿头鸭实现了{@link Quackable}接口
 * @author HXS
 * @copyright
 * @since 2019-07-23
 */
public class MallardDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("quack");
    }
}
