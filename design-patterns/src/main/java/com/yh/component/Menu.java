package com.yh.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 菜单
 *
 * @author yanhuan
 */
public class Menu extends MenuComponent {

    private List<MenuComponent> MENU_COMPONENT_LIST = new ArrayList<>();

    private String name;

    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        MENU_COMPONENT_LIST.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        MENU_COMPONENT_LIST.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return MENU_COMPONENT_LIST.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void print() {
        System.out.println("Name:" + getName());
        System.out.println("DESC:" + getDescription());
        Iterator<MenuComponent> iterator = MENU_COMPONENT_LIST.iterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            next.print();
        }
    }
}
