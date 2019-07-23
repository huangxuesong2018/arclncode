package com.arcln.pattern.composite.decorator;

import com.arcln.pattern.composite.Quackable;

/**
 * 《装饰器模式》
 * 统计鸭子呱呱叫的次数
 *  1,实现鸭子的接口
 *  2，包含鸭子的引用
 * @author HXS
 * @copyright
 * @since 2019-07-23
 */
public class CountDecorator implements Quackable {
    Quackable quackable;
    static int count= 0;
    public CountDecorator(Quackable quackable) {
        this.quackable = quackable;
    }

    @Override
    public void quack() {
        quackable.quack();
        count ++;
    }

    public static int getCount() {
        return count;
    }
}
