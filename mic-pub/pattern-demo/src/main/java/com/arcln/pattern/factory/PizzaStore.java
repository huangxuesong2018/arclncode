package com.arcln.pattern.factory;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-02
 */
public abstract class PizzaStore {
    /**
     * 流程不允许子类修改
     * @param type
     * @return
     */
    public final Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    public abstract Pizza createPizza(String type);
}
