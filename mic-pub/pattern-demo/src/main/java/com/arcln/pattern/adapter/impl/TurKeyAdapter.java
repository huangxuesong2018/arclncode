package com.arcln.pattern.adapter.impl;

import com.arcln.pattern.adapter.Duck;
import com.arcln.pattern.adapter.Turkey;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-15
 */
public class TurKeyAdapter implements Duck {
    private Turkey turKey;

    public TurKeyAdapter(Turkey turKey) {
        this.turKey = turKey;
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++){
            turKey.fly();
        }
    }

    @Override
    public void quack() {
        turKey.gobble();
    }
}
