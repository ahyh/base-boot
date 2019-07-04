package com.yh.java8.stream;

import com.yh.java8.pojo.Apple;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 收集器的使用
 *
 * @author yanhuan
 */
public class CollectorsDemo {

    /**
     * 将颜色为color的Apple放在一个集合中，返回集合
     *
     * @param color  颜色
     * @param apples 待分类的集合
     * @return 颜色为color的Apple集合
     */
    private static List<Apple> colorApple(String color, List<Apple> apples) {
        return apples.stream()
                .filter(apple -> color.equals(apple.getColor()))
                .sorted(Comparator.comparing(Apple::getWeight))
                .collect(Collectors.toList());
    }

    /**
     * 将apples按颜色进行分类
     *
     * @param apples 待分类的Apple集合
     * @return 颜色和Apple集合的映射Map
     */
    private static Map<String, List<Apple>> color2ApplesMap(List<Apple> apples) {
        return apples.stream().
                collect(Collectors.groupingBy(apple -> apple.getColor()));
    }

    public static void main(String[] args) {
        colorApple("red", Apple.apples).forEach(System.out::println);
        Map<String, List<Apple>> color2ApplesMap = color2ApplesMap(Apple.apples);
        System.out.println(color2ApplesMap);

        //汇总，计算所有的Apple的weight的总和
        Integer sumWeight = Apple.apples.stream().collect(Collectors.summingInt(Apple::getWeight));
        System.out.println(sumWeight);

        //找出重量最大的Apple
        Optional<Apple> maxWeightApple = Apple.apples.stream().max(Comparator.comparing(Apple::getWeight));
        System.out.println(maxWeightApple.get());

        //获取Apple的weight的平均值
        Double collect = Apple.apples.stream().collect(Collectors.averagingInt(apple -> apple.getWeight()));
        System.out.println("Average" + collect);

        //获取Apple中apples集合的weight的统计信息
        IntSummaryStatistics statistics = Apple.apples.stream().collect(Collectors.summarizingInt(Apple::getWeight));
        System.out.println(statistics);

        //将Apple集合中color提取出来，去重后使用,连接成一个字符串
        String colors = Apple.apples.stream().map(apple -> apple.getColor()).distinct().collect(Collectors.joining(","));
        System.out.println(colors);

        //多级分组
        Map<String, Map<String, List<Apple>>> map = Apple.apples.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.groupingBy(apple -> {
            if (apple instanceof Apple) {
                Apple a = apple;
                if (a.getWeight() < 110) {
                    return "light";
                } else if (a.getWeight() > 110 && a.getWeight() <= 130) {
                    return "middle";
                } else {
                    return "heavy";
                }
            }
            return "";
        })));
        System.out.println(map);

        //统计各种颜色Apple的数量
        Map<String, Long> color2SumMap = Apple.apples.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.counting()));
        System.out.println(color2SumMap);

        //按颜色分组后找出各组weight最大的Apple
        Map<String, Optional<Apple>> color2MaxWeightAppleMap = Apple.apples.stream().
                collect(Collectors.groupingBy(Apple::getColor, Collectors.maxBy(Comparator.comparing(Apple::getWeight))));
        System.out.println(color2MaxWeightAppleMap);

        //求出每一种颜色Apple的weight总和，按颜色分类
        Map<String, Integer> color2SumWeightMap = Apple.apples.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.summingInt(Apple::getWeight)));
        System.out.println(color2SumWeightMap);



    }
}
