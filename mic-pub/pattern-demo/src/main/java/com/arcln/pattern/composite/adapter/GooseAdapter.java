package com.arcln.pattern.composite.adapter;

import com.arcln.pattern.composite.Quackable;

/**
 * 鹅的适配器，实现鸭子的接口
 * @author HXS
 * @copyright
 * @since 2019-07-23
 */
public class GooseAdapter implements Quackable {
    Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }
}
