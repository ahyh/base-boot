package com.yh.java8.al4.leetcode.sort;

/**
 * 选择排序
 * 首先找到数组中最大（小）的元素，将它和第一个元素交换位置
 * 从剩下的元素找出最大（小）的元素，将它和对应位置的元素交换位置
 * 重复上述的步骤
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 5, 9, 8, 7};
        sort(array);
        System.out.println(array.toString());
    }

    public static int[] sort(int[] array) {
        if (array == null || array.length == 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                // 比较并替换min的值
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            // for循环结束后，min就是第i循环找出的第i个排序的元素
            if (i != min) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
        return array;
    }
}
