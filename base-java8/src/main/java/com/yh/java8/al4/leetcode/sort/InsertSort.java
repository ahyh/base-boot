package com.yh.java8.al4.leetcode.sort;

/**
 * 插入排序
 */
public class InsertSort {

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
            // 将array[i]插入到a[i-1], a[i-2], a[i-3]...之中
            for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {
                exchange(array, j, j - 1);
            }
        }
        return array;
    }

    public static boolean less(int i, int j) {
        return i < j;
    }

    public static void exchange(int[] array, int i, int j){
        int temp = array[i];
        array[i]= array[j];
        array[j] = temp;
    }
}
