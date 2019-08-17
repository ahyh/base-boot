package com.yh.decorator;

/**
 * 装饰者模式
 *
 * @author yanhuan
 */
public class DecoratorMain {

    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + "$" + espresso.cost());

        Beverage beverage = new DarkRoast();
        beverage = new Mocha(beverage);
        beverage = new Mocha(beverage);
        beverage = new Sugar(beverage);
        System.out.println(beverage.getDescription() + "$" + beverage.cost());
    }
}
