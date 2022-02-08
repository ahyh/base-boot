package com.yh.java8.al4.leetcode.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author yanhuan
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 5, 9, 8, 7};
        int[] sort = sort(array);
        System.out.println(sort);
    }

    public static int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(sort(left), sort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }
}
