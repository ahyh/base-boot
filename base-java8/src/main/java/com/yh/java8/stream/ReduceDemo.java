package com.yh.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 归约操作
 */
public class ReduceDemo {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //求和，接受一个初始值
        Integer reduce = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(reduce);

        Integer reduce1 = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce1);

        //找出最大值
        Optional<Integer> maxReduce1 = list.stream().reduce((a, b) -> a > b ? a : b);
        Optional<Integer> maxReduce = list.stream().reduce(Integer::max);
        System.out.println(maxReduce.get());
    }
}
