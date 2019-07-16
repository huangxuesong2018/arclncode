package com.arcln.pattern.decorator.beverages;

import com.arcln.pattern.decorator.Beverage;

/**
 * 混合咖啡
 * @author HXS
 * @copyright
 * @since 2019-06-28
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
