package com.yh.state;

import com.yh.state.impl.HasQuarterState;
import com.yh.state.impl.NoQuarterState;
import com.yh.state.impl.SoldState;
import com.yh.state.impl.SoldoutState;
import com.yh.state.impl.WinnerState;

/**
 * 糖果机，持有多种状态
 *
 * @author yanhuan
 */
public class GumballMachine {

    private State soldoutState;

    private State noQuarterState;

    private State hasQuarterState;

    private State soldState;

    private State winnerState;

    private State state = soldoutState;

    private int count = 0;

    public GumballMachine(int numberGumballs) {
        this.soldoutState = new SoldoutState(this);
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.soldState = new SoldState(this);
        this.winnerState = new WinnerState(this);
        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter(){
        state.insertQuarter();
    }

    public void turnCrank(){
        state.turnCrank();
    }

    public void ejectQuarter(){
        state.ejectQuarter();
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getSoldoutState() {
        return soldoutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public State getState() {
        return state;
    }

    public int getCount() {
        return count;
    }

    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count--;
        }
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state.getClass().getSimpleName() +
                ", count=" + count +
                '}';
    }
}
