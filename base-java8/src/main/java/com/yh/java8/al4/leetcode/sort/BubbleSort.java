package com.yh.java8.al4.leetcode.sort;

/**
 * 冒泡排序
 *
 * @author yanhuan
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 5, 9, 8, 7};
        sort(array);
        System.out.println(array);
    }

    public static int[] sort(int[] array) {
        if (array == null || array.length == 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                // 比较并交换
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

}
