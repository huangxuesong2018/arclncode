package com.arcln.pattern.factory;

import com.arcln.pattern.factory.stores.ChicagoPizzaStore;
import com.arcln.pattern.factory.stores.NYPizzaStore;

/**
 * 工厂模式
 * @author HXS
 * @copyright
 * @since 2019-07-02
 */
public class Test {
    public static void main(String[] args) {
        PizzaStore store = new NYPizzaStore();
        store.orderPizza("cheese");

        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        System.out.println(chicagoPizzaStore.orderPizza("cheese"));
    }

}

