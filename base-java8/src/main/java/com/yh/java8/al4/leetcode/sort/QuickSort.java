package com.yh.java8.al4.leetcode.sort;

/**
 * 快速排序
 * 任意选取一个数据，作为基准数，然后将所有比它小的数据都放在它前面，所有比它大的数据都放在它后面
 * 这个过程称为一趟快速排序，也称为分区操作
 * 通过一次分区操作将要排序的数据分为两部分，其中一部分都比另一部小，
 *
 * @author yanhuan
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 5, 9, 8, 7};
        sort(array, 0, array.length - 1);
        System.out.println(array);
    }

    public static int[] sort(int[] array, int start, int end) {
        if (array == null || start < 0 || end >= array.length || start > end) {
            return null;
        }
        int partition = partition(array, start, end);
        if (partition > start) {
            sort(array, start, partition - 1);
        }
        if (partition < end) {
            sort(array, partition + 1, end);
        }
        return array;
    }

    public static int partition(int[] array, int start, int end) {
        // 仅有一个元素的时候不需要在分区
        if (start == end) {
            return start;
        }
        // 随机选择一个基准数
        int partition = (int) (start + Math.random() * (end - start + 1));
        // zoneIndex是分区指示器，初始值为分区头元素下标减1
        int zoneIndex = start - 1;
        swap(array, partition, end);
        for (int i = start; i <= end; i++) {
            // 当前元素小于基准数
            if (array[i] <= array[end]) {
                // 首先分区指示器累加
                zoneIndex++;
                if (i > zoneIndex) {
                    // 当前元素在分区指示器右边时，交换当前元素和分区指示器元素
                    swap(array, i, zoneIndex);
                }
            }
        }
        return zoneIndex;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
