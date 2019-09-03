package com.yh.state.impl;

import com.yh.state.GumballMachine;
import com.yh.state.State;

import java.util.Random;

/**
 * 投入硬币后状态
 *
 * @author yanhuan
 */
public class HasQuarterState implements State {

    private static final Random random = new Random();

    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You has already insert quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Quarter returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
        int randomInt = random.nextInt(100);
        if (randomInt < 10 && gumballMachine.getCount() > 1) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        } else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }

}
