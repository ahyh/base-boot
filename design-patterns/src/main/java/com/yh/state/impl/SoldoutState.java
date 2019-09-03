package com.yh.state.impl;

import com.yh.state.GumballMachine;
import com.yh.state.State;

public class SoldoutState implements State {

    private GumballMachine gumballMachine;

    public SoldoutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("gumball sold out,cannot insert quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("cannot eject quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("cannot turn crank");
    }

    @Override
    public void dispense() {
        System.out.println("cannot dispense");
    }
}
