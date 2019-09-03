package com.yh.state;

/**
 * 状态模式测试类
 *
 * @author yanhuan
 */
public class GumballMachineMain {

    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(10);
        System.out.println(gumballMachine);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);
    }
}
