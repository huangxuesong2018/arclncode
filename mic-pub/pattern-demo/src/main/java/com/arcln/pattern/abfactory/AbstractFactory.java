package com.arcln.pattern.abfactory;

/**
 * 抽象工厂
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public interface AbstractFactory {
    public AbstractProductA createProductA();
    public AbstractProductB createProductB();
}
