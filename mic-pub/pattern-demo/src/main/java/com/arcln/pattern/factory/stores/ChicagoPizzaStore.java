package com.arcln.pattern.factory.stores;

import com.arcln.pattern.factory.Pizza;
import com.arcln.pattern.factory.PizzaStore;
import com.arcln.pattern.factory.pizzas.*;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-02
 */
public class ChicagoPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null ;
        if ("cheese".equals(type)){
            pizza = new ChicagoStyleCheesePizza();
        }else if("veggie".equals(type)){
            pizza = new ChicagoStyleVeggiePizza();
        }else if("pepperoni".equals(type)){
            pizza = new ChicagoStylePepperoniPizza();
        }
        return pizza;
    }
}
