package com.yh.component;

public class MenuComponentMain {

    public static void main(String[] args) {
        MenuComponent menu1 = new Menu("PHM", "Breakfast");
        MenuComponent menu2 = new Menu("DINER MENU", "Lunch");
        MenuComponent menu3 = new Menu("CAFE MENU", "Diner");
        MenuComponent menu4 = new Menu("DESSERT MENU", "Diner");

        MenuComponent allMenus = new Menu("All Menus", "all menus combined");

        allMenus.add(menu1);
        allMenus.add(menu2);
        allMenus.add(menu3);
        allMenus.add(menu4);

        menu1.add(new MenuItem("Pas", "aaa", 11.11, true));
        menu3.add(new MenuItem("Abc", "def", 23.11, true));

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();
    }
}
