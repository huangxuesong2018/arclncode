package com.arcln.pattern.decorator;

/**
 * 调味剂装饰者类
 * @author HXS
 * @copyright
 * @since 2019-06-28
 */
public abstract class CondimentDecorator extends Beverage {
    //继承它同时又拥有它
    protected Beverage beverage;

    @Override
    public abstract String getDescription();
}
