package com.yh.java8.lambda.predicate;

import com.yh.java8.pojo.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 测试一下Predicate断言接口
 * 筛选苹果
 *
 * @author yanhuan
 */
public class FilterApple {

    /**
     * 筛选苹果方法
     *
     * @param apples    苹果集合
     * @param predicate 断言接口
     * @return 筛选出来的苹果
     */
    public static List<Apple> filterApple(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        //筛选出颜色为red的苹果
        List<Apple> redApples = filterApple(Apple.apples, apple -> "red".equals(apple.getColor()));
        redApples.forEach(System.out::println);
        System.out.println("=====================================");

        //筛选出重量超过120的苹果
        List<Apple> weightApples = filterApple(Apple.apples, apple -> apple.getWeight() >= 120);
        weightApples.forEach(System.out::println);
        System.out.println("=====================================");

        //串行流筛选颜色为red的苹果
        List<Apple> redAppleList = Apple.apples.stream().filter(apple -> "red".equals(apple.getColor())).collect(Collectors.toList());
        redAppleList.forEach(System.out::println);
        System.out.println("=====================================");

        //并行流筛选重量大于120的苹果
        List<Apple> weightAppleList = Apple.apples.parallelStream().filter(apple -> apple.getWeight() >= 120).collect(Collectors.toList());
        weightAppleList.parallelStream().forEach(System.out::println);
    }


}

