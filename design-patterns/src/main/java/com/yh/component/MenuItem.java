package com.yh.component;

/**
 * 菜单项
 *
 * @author yanhuan
 */
public class MenuItem extends MenuComponent {

    private String name;
    private String description;
    private double price;
    boolean vegetarian;

    public MenuItem(String name, String description, double price, boolean vegetarian) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.vegetarian = vegetarian;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public void print() {
        System.out.println("MenuItem:" + this.name);
        if (isVegetarian()) {
            System.out.println("v");
        }
        System.out.println(getPrice());
        System.out.println("==" + getDescription());
    }
}
