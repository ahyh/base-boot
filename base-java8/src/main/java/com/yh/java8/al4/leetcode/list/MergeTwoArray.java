package com.yh.java8.al4.leetcode.list;

import java.util.Arrays;

/**
 * 合并两个非递减数组，是合并后的数组仍然是非递减的
 * eg. array1 = {1,2,2,3}; array2={2,3,6};
 * 那么merge后的数组array1 = {1,2,2,2,3,3,6}
 *
 * @author yanhuan
 */
public class MergeTwoArray {

    private static final int[] array1 = {1, 2, 2, 3};
    private static final int[] array2 = {2, 3, 5, 5, 8};

    /**
     * 通过jdk的Arrays.sort方法进行merge
     */
    private static int[] mergeViaJDKMethod(int[] array1, int[] array2) {
        int len1 = array1.length;
        int len2 = array2.length;
        int[] temp = new int[len1 + len2];
        int i = 0;
        for (; i < len1; i++) {
            temp[i] = array1[i];
        }
        for (; i < len1 + len2; i++) {
            temp[i] = array2[i - len1];
        }
        Arrays.sort(temp);
        return temp;
    }

    /**
     * 通过双指针的方式完成merge
     */
    private static int[] mergeViaTwoPointer(int[] array1, int[] array2) {
        int len1 = array1.length;
        int len2 = array2.length;
        int[] temp = new int[len1 + len2];
        int index1 = 0, index2 = 0;
        for (int index = 0; index < len1 + len2; index++) {
            if (index1 > len1 - 1) {
                // 如果array1全部取完，直接从array2中取即可
                temp[index] = array2[index2++];
            } else if (index2 > len2 - 1) {
                // 如果array2全部取完，直接从array1中取即可
                temp[index] = array1[index1++];
            } else if (array1[index1] > array2[index2]) {
                // array1中数组元素大于array2中数组元素，取较小的那个，同时对应的array指针向后移动一位
                temp[index] = array2[index2++];
            } else {
                temp[index] = array1[index1++];
            }
        }
        return temp;
    }

    /**
     * 通过双尾指针完成merge, 可以不需要额外的存储空间，即临时数组，但是传入的参数，array1需要额外的空间
     */
    private static int[] mergeViaTwoTailPointer(int[] array1, int m, int[] array2, int n) {
        int k = m + n;
        for (int index = k - 1, index1 = m - 1, index2 = n - 1; index >= 0; index--) {
            if (index1 < 0) {
                // array1中元素已经取完，直接取array2中元素即可
                array1[index] = array2[index2--];
            } else if (index2 < 0) {
                // array2中元素已经取完，完全去array1中数组即可，不需要在进行处理了
                break;
            } else if (array1[index1] > array2[index2]) {
                // 比较大小，移动尾指针即可
                array1[index] = array1[index1--];
            } else {
                array1[index] = array2[index2--];
            }
        }
        return array1;
    }

    public static void main(String[] args) {
//        int[] result = mergeViaJDKMethod(array1, array2);
//        int[] result = mergeViaTwoPointer(array1, array2);
        int[] arr1 = {1, 2, 2, 3, 0, 0, 0};
        int[] arr2 = {1, 4, 8};
        int[] result = mergeViaTwoTailPointer(arr1, 4, arr2, 3);
        System.out.println(result);
    }

}
