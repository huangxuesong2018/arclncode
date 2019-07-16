package com.arcln.pattern.decorator;

import com.arcln.pattern.decorator.beverages.HouseBlend;
import com.arcln.pattern.decorator.drs.Mocha;
import com.arcln.pattern.decorator.drs.Soy;
import com.arcln.pattern.decorator.drs.Whip;

import java.io.*;


/**
 * 装饰模式
 * @author HXS
 * @copyright
 * @since 2019-06-28
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Beverage beverage = new HouseBlend();
        beverage = new Mocha(beverage);
        beverage = new Mocha(beverage);
        beverage = new Soy(beverage);
        beverage = new Whip(beverage);
        System.out.println(beverage.getDescription()+","+beverage.cost());

    }
}
