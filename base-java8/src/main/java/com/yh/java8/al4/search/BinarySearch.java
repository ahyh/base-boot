package com.yh.java8.al4.search;

/**
 * 二分查找算法
 *
 * @author yanhuan
 */
public class BinarySearch {

    /**
     * 二分查找算法，如果key存在数组array中，就返回key的index, 否则返回-1
     *
     * @param key   查找的key
     * @param array 查找的数组
     * @return index of key in array
     */
    public static <T> int rank(Comparable<T> key, Comparable<T>[] array) {
        int l = 0;
        int h = array.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int compare = key.compareTo((T) array[mid]);
            if (compare < 0) {
                h = mid - 1;
            } else if (compare > 0) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 3, 4, 6, 7, 9};
        int rank = rank(7, array);
        System.out.println(rank);
    }
}
