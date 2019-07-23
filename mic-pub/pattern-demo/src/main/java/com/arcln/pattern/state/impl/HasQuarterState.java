package com.arcln.pattern.state.impl;

import com.arcln.pattern.state.GumballMachine;
import com.arcln.pattern.state.State;

import java.util.Random;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-19
 */
public class HasQuarterState implements State{
    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("你不能再投入quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("退钱了");
        gumballMachine.setState(gumballMachine.getNoQuarterStare());
    }

    @Override
    public void turnCrank() {
        System.out.println("转起来");
        int random = new Random().nextInt(10);
        if (random == 0 && (gumballMachine.getCount() > 0)){
            gumballMachine.setState(gumballMachine.getWinnerState());
        }else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }

    }

    @Override
    public void dispense() {
        System.out.println("这里不发糖，等转起来再说吧..");
    }
}
