package com.yh.java8.stream;

import com.yh.java8.pojo.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 流式编程例子
 *
 * @author yanhuan
 */
public class AppleStream {

    /**
     * 流式编程获取red苹果并按照weight排序，取出前2个
     *
     * @param apples 待筛选的苹果集合
     * @return 满足条件的苹果集合
     */
    private static List<Apple> filterRedApplesOrderByWeight(List<Apple> apples) {
        return apples.stream().filter(apple -> "red".equals(apple.getColor()))
                .sorted(Comparator.comparing(Apple::getWeight).reversed())
                .limit(2)
                .collect(Collectors.toList());
    }

    /**
     * 流式编程按Color对苹果进行分类，返回Map，key是颜色，value是颜色对应的苹果的集合
     *
     * @param apples 待分类的苹果集合
     * @return 颜色和苹果集合的Map
     */
    private static Map<String, List<Apple>> groupAppleByColor(List<Apple> apples) {
        return apples.parallelStream().collect(Collectors.groupingBy(Apple::getColor));
    }

    /**
     * flatMap方法，获取扁平流
     *
     * @param args 字符串数组
     * @return 字符串数组中单个字符集合，去重
     */
    private static List<String> flatMapString(String[] args) {
        return Arrays.stream(args)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Apple> apples = filterRedApplesOrderByWeight(Apple.apples);
        apples.forEach(System.out::println);

        Map<String, List<Apple>> map = groupAppleByColor(Apple.apples);
        for (Map.Entry<String, List<Apple>> entry : map.entrySet()) {
            List<Apple> value = entry.getValue();
            System.out.println("Color:" + entry.getKey());
            value.forEach(System.out::println);
            System.out.println("=========================");
        }

        String[] wordList = {"Hello", "world"};
        List<String> charsList = flatMapString(wordList);
        charsList.forEach(System.out::println);
    }


}

