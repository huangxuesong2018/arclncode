package com.arcln.pattern.state.impl;

import com.arcln.pattern.state.GumballMachine;
import com.arcln.pattern.state.State;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-19
 */
public class SoldState implements State {
    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("给过钱了，稍等一会有糖发出来");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("转过了，不能退钱了");
    }

    @Override
    public void turnCrank() {
        System.out.println("别转了，再转也不会给糖了");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
       if(gumballMachine.getCount() > 0){
           gumballMachine.setState(gumballMachine.getNoQuarterStare());
       }else{
           System.out.println("卖完了，没货了");
           gumballMachine.setState(gumballMachine.getSoldOutState());
       }
    }
}
