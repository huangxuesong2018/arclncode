package com.arcln.pattern.state.impl;

import com.arcln.pattern.state.GumballMachine;
import com.arcln.pattern.state.State;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-19
 */
public class SoldOutState implements State {
    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("没有糖了，收不了钱");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("没有糖了，收不了钱，退啥钱呢");
    }

    @Override
    public void turnCrank() {
        System.out.println("不能转动，没给钱");
    }

    @Override
    public void dispense() {
        System.out.println("没有糖了");
    }
}
