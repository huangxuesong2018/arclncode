package com.arcln.pattern.adapter.impl;

import com.arcln.pattern.adapter.Turkey;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-15
 */
public class WildTurkey implements Turkey {
    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }

    @Override
    public void gobble() {
        System.out.println("Gobble Gobble");
    }
}
