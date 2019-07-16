package com.arcln.pattern.decorator.drs;

import com.arcln.pattern.decorator.Beverage;
import com.arcln.pattern.decorator.CondimentDecorator;

/**
 * 摩卡
 * @author HXS
 * @copyright
 * @since 2019-06-28
 */
public class Mocha extends CondimentDecorator {
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + 0.20;
    }
}
