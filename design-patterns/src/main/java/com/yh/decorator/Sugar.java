package com.yh.decorator;

/**
 * 调料，糖
 *
 * @author yanhuan
 */
public class Sugar extends CondimentDecorator {

    private Beverage beverage;

    public Sugar(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Sugar";
    }

    @Override
    public double cost() {
        return 0.5 + beverage.cost();
    }
}
