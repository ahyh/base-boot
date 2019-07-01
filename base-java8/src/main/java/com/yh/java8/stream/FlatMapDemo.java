package com.yh.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * flatMap的使用
 *
 * @author yanhuan
 */
public class FlatMapDemo {

    private static List<Integer> nums1 = Arrays.asList(1, 2, 3);

    private static List<Integer> nums2 = Arrays.asList(3, 4);

    public static void main(String[] args) {
        //获取一一映射的集合
        List<int[]> list = nums1.stream().
                flatMap(i -> nums2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        list.forEach(x -> System.out.println(x[0] + ":" + x[1]));


        //获取一一映射的集合并且两个数的和可以被3整除
        List<int[]> list2 = nums1.stream().
                flatMap(i -> nums2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        list2.forEach(x -> System.out.println(x[0] + ":" + x[1]));
    }

}
