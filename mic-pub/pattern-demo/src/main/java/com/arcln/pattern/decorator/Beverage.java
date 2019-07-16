package com.arcln.pattern.decorator;

/**
 * 饮料
 * @author HXS
 * @copyright
 * @since 2019-06-28
 */
public abstract class Beverage {
    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();


}
