package com.yh.decorator;

/**
 * 调料装饰
 *
 * @author yanhuan
 */
public abstract class CondimentDecorator extends Beverage {

    @Override
    public abstract String getDescription();
}
