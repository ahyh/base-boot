package com.yh.java8.al4.leetcode.list;

/**
 * 将所有0移动到数组末尾，其他元素相对顺序保持不变
 *
 * @author yanhuan
 */
public class MoveZero {

    /**
     * 适应两个指针来完成move zero
     */
    private static int[] moveZero(int[] array) {
        int i = 0;
        int j = 0;
        // 第一次遍历，遇到非零元素时，将array[i]赋给array[j], 同时j++
        for (; i < array.length; i++) {
            if (array[i] != 0) {
                array[j++] = array[i];
            }
        }
        // 第二次遍历，从j开始的元素都是0
        for (; j < array.length; j++) {
            array[j] = 0;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 3, 0, 5, 7};
        moveZero(array);
        System.out.println(array);
    }
}
