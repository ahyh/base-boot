package com.yh.component;

/**
 * 服务员类，持有一个MenuComponent对象
 *
 * @author yanhuan
 */
public class Waitress {

    private MenuComponent menuComponent;

    public Waitress(MenuComponent menuComponent) {
        this.menuComponent = menuComponent;
    }

    public void printMenu() {
        menuComponent.print();
    }
}
