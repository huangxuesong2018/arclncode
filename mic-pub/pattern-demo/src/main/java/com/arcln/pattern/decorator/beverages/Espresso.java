package com.arcln.pattern.decorator.beverages;

import com.arcln.pattern.decorator.Beverage;

/**
 * 浓缩咖啡
 * @author HXS
 * @copyright
 * @since 2019-06-28
 */
public class Espresso extends Beverage{
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }


}
