package com.arcln.pattern.state.impl;

import com.arcln.pattern.state.GumballMachine;
import com.arcln.pattern.state.State;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-19
 */
public class WinnerState implements State {
    GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("----");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("----");
    }

    @Override
    public void turnCrank() {
        System.out.println("----");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() == 0){
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }else {
            gumballMachine.releaseBall();
            if(gumballMachine.getCount() > 0){
                gumballMachine.setState(gumballMachine.getNoQuarterStare());
            }else{
                System.out.println("卖完了，没货了");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }
}
