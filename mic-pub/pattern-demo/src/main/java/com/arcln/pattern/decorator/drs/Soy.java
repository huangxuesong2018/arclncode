package com.arcln.pattern.decorator.drs;

import com.arcln.pattern.decorator.Beverage;
import com.arcln.pattern.decorator.CondimentDecorator;

/**
 * 大豆
 * @author HXS
 * @copyright
 * @since 2019-06-28
 */
public class Soy extends CondimentDecorator {
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + 0.5;
    }
}
