package com.arcln.pattern.abfactory;

import com.arcln.pattern.abfactory.facs.ConcreteFactory1;
import com.arcln.pattern.abfactory.facs.ConcreteFactory2;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new ConcreteFactory1();
        System.out.println(factory.createProductB());

        AbstractFactory factory2 = new ConcreteFactory2();
        System.out.println(factory2.createProductB());
    }
}
