package com.yh.java8.al4.leetcode.sort;

/**
 * 堆排序
 *
 * @author yanhuan
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 5,11, 9, 8, 7};
        sort(array);
        System.out.println(array);
    }

    public static void buildMaxHeap(int[] array) {
        int len = array.length;
        // 从最后一个非叶子节点上构建最大堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, len);
        }
    }

    public static void adjustHeap(int[] array, int index, int len) {
        int maxIndex = index;
        int left = 2 * index + 1;
        int right = 2 * (index + 1);
        if (left < len && array[left] > array[maxIndex]) {
            maxIndex = left;
        }
        if (right < len && array[right] > array[maxIndex]) {
            maxIndex = right;
        }
        if (maxIndex != index) {
            swap(array, maxIndex, index);
            adjustHeap(array, maxIndex, len);
        }
    }

    public static int[] sort(int[] array) {
        int len = array.length;
        if (len < 1) {
            return array;
        }
        buildMaxHeap(array);
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0, len);
        }
        return array;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
