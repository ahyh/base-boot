package com.yh.java8.al4.leetcode.bit;

/**
 * 找出数组中只出现一次的元素
 *
 * @author yanhuan
 */
public class OnlyOneElement {

    public static void main(String[] args) {
        int[] array = {1, 1, 2, 3, 4, 5, 4, 3, 5};
        int result = onlyOneElement(array);
        System.out.println(result);
    }

    /**
     * 给定一个整数数组，只有一个元素出现一次，其他元素均出现两次，找出出现一次的元素
     */
    private static int onlyOneElement(int[] array) {
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            result = result ^ array[i];
        }
        return result;
    }
}
