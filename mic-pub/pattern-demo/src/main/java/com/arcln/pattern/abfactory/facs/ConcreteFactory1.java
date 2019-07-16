package com.arcln.pattern.abfactory.facs;

import com.arcln.pattern.abfactory.AbstractFactory;
import com.arcln.pattern.abfactory.AbstractProductA;
import com.arcln.pattern.abfactory.AbstractProductB;
import com.arcln.pattern.abfactory.pros.ProductA1;
import com.arcln.pattern.abfactory.pros.ProductB1;


/**
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
