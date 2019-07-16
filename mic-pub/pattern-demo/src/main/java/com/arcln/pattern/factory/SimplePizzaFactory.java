package com.arcln.pattern.factory;

import com.arcln.pattern.factory.pizzas.CheesePizza;
import com.arcln.pattern.factory.pizzas.PepperoniPizza;
import com.arcln.pattern.factory.pizzas.VeggiePizza;

/**
 * 简单工厂
 * @author HXS
 * @copyright
 * @since 2019-07-02
 */
public class SimplePizzaFactory {
    public static Pizza createPizza(String type){
        Pizza pizza = null ;
        if ("cheese".equals(type)){
            pizza = new CheesePizza();
        }else if("veggie".equals(type)){
            pizza = new VeggiePizza();
        }else if("pepperoni".equals(type)){
            pizza = new PepperoniPizza();
        }
        return pizza;
    }
}
