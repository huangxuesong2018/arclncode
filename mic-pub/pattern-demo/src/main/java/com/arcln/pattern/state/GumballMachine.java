package com.arcln.pattern.state;

import com.arcln.pattern.state.impl.*;

/**
 * Context角色
 * 糖果机
 * @author HXS
 * @copyright
 * @since 2019-07-19
 */
public class GumballMachine{
    State soldOutState;
    State soldState;
    State hasQuarterState;
    State noQuarterStare;
    State winnerState;

    State state = soldOutState;
    int count = 0;

    public GumballMachine(int numberGumball) {
        this.soldOutState = new SoldOutState(this);
        this.soldState = new SoldState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.noQuarterStare = new NoQuarterState(this);
        this.winnerState = new WinnerState(this);
        this.count = numberGumball;
        if (numberGumball > 0){
            this.state = noQuarterStare;
        }
    }

    public void insertQuarter(){
        this.state.insertQuarter();
    }

    /**
     * 退出25分钱硬币
     */
    void ejectQuarter(){
        this.state.ejectQuarter();
    }
    /**
     * 旋转曲柄
     */
    public void turnCrank(){
        this.state.turnCrank();
        this.state.dispense();
    }

    /**
     * 发放糖
     */
    public void releaseBall(){
        System.out.println("1个糖果从机器里面滚了出来");
        if (count != 0){
            count = count - 1;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getNoQuarterStare() {
        return noQuarterStare;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public int getCount() {
        return count;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "soldOutState=" + soldOutState +
                ", soldState=" + soldState +
                ", hasQuarterState=" + hasQuarterState +
                ", noQuarterStare=" + noQuarterStare +
                ", winnerState=" + winnerState +
                ", state=" + state +
                ", count=" + count +
                '}';
    }
}
