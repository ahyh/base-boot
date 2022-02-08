package com.yh.java8.al4.leetcode.sort;

/**
 * 希尔排序
 *
 * @author yanhuan
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 5, 9, 8, 7};
        sort(array);
        System.out.println(array.toString());
    }

    public static int[] sort(int[] array) {
        int len = array.length;
        // 按增量分组后，每个分组中，temp代表当前的待排序数据，该元素之前的组内元素均已被排序过
        int currentValue, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                currentValue = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > currentValue) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = currentValue;
            }
            gap /= 2;
        }
        return array;
    }

    public static boolean less(int i, int j) {
        return i < j;
    }

    public static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
