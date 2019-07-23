package com.arcln.pattern.state.impl;

import com.arcln.pattern.state.GumballMachine;
import com.arcln.pattern.state.State;

/**
 * 没有投币的状态操作
 * @author HXS
 * @copyright
 * @since 2019-07-19
 */
public class NoQuarterState implements State{
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("投币了");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("没有给钱，就不能要求退钱");
    }

    @Override
    public void turnCrank() {
        System.out.println("没有给钱，不能旋转");
    }

    @Override
    public void dispense() {
        System.out.println("没有给钱，不给糖果");
    }
}
