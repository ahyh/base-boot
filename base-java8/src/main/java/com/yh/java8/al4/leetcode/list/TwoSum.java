package com.yh.java8.al4.leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 找出数组中两个元素和为给定值的两个下标
 *
 * @author yanhuan
 */
public class TwoSum {

    public int[] twoSum(int[] array, int target) {
        // key为元素值，value为下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int another = target - array[i];
            Integer anotherIndex = map.get(another);
            if (anotherIndex != null) {
                return new int[]{i, anotherIndex};
            } else {
                map.put(array[i], i);
            }
        }
        return new int[2];
    }
    
}
