package com.yh.state.impl;

import com.yh.state.GumballMachine;
import com.yh.state.State;

/**
 * 未投硬币状态
 *
 * @author yanhuan
 */
public class NoQuarterState implements State {

    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You insert a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Your haven't inserted a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned,but there is not quarter");
    }

    @Override
    public void dispense() {
        System.out.println("You need to pay first");
    }
}
