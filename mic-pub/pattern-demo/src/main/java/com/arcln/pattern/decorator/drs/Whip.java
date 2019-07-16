package com.arcln.pattern.decorator.drs;

import com.arcln.pattern.decorator.Beverage;
import com.arcln.pattern.decorator.CondimentDecorator;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-28
 */
public class Whip extends CondimentDecorator{
    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription()+ ",Whip";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + .44;
    }
}
