package com.yh.java8.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Apple implements Serializable {

    private String color;

    private Integer weight;

    public static List<Apple> apples = new ArrayList();

    static {
        apples.add(new Apple("red", 100));
        apples.add(new Apple("red", 120));
        apples.add(new Apple("red", 150));
        apples.add(new Apple("green", 100));
        apples.add(new Apple("green", 120));
        apples.add(new Apple("green", 150));
        apples.add(new Apple("green", 180));
        apples.add(new Apple("yellow", 100));
        apples.add(new Apple("yellow", 130));
        apples.add(new Apple("yellow", 150));
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
