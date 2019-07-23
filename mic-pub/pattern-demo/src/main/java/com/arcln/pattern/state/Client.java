package com.arcln.pattern.state;

import com.arcln.pattern.proxy.GumballMachineProxy;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-19
 */
public class Client {
    public static void main(String[] args) {
        GumballMachine machine = new GumballMachine(10);
        for (int i = 0; i < 5; i ++){
            machine.insertQuarter();
            machine.turnCrank();
            System.out.println("-------------------------");
        }

    }
}
