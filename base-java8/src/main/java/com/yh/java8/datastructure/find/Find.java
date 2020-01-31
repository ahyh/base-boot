package com.yh.java8.datastructure.find;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找
 *
 * @author yanhuan
 */
public class Find {

    /**
     * 二分查找，需要elements是一个有序数组
     *
     * @param elements 待查找的集合
     * @param value    查找的值
     * @return 下标，如果没有找到则返回-1
     */
    public static int binarySearch(Comparable[] elements, Comparable value) {
        return binarySearch(elements, value, 0, elements.length);
    }

    public static List<Integer> binarySearchAll(Comparable[] elements, Comparable value) {
        return binarySearchAll(elements, value, 0, elements.length);
    }

    /**
     * 找出集合中的一个等于value元素的下标
     */
    private static int binarySearch(Comparable[] elements, Comparable value, int left, int right) {
        int mid = (left + right) / 2;
        if (elements[mid].compareTo(value) > 0) {
            return binarySearch(elements, value, 0, mid);
        } else if (elements[mid].compareTo(value) < 0) {
            return binarySearch(elements, value, mid + 1, right);
        } else {
            return mid;
        }
    }

    /**
     * while循环的方式进行二分查找
     *
     * @param elements 待查找的数组
     * @param value    待查找的值
     * @return 元素的下标
     */
    public static int binarySearch2(Comparable[] elements, Comparable value) {
        int left = 0, right = elements.length;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (elements[mid].compareTo(value) > 0) {
                right = mid - 1;
            } else if (elements[mid].compareTo(value) < 0) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 找出集合中的所有等于value元素的下标
     */
    private static List<Integer> binarySearchAll(Comparable[] elements, Comparable value, int left, int right) {
        int mid = (left + right) / 2;
        if (elements[mid].compareTo(value) > 0) {
            return binarySearchAll(elements, value, 0, mid);
        } else if (elements[mid].compareTo(value) < 0) {
            return binarySearchAll(elements, value, mid + 1, right);
        } else {
            List<Integer> indexList = new ArrayList<>();
            //向左查看
            int temp = mid;
            while (temp >= 0) {
                if (elements[temp].compareTo(elements[mid]) == 0) {
                    indexList.add(temp);
                    temp--;
                } else {
                    break;
                }
            }
            //向右查看
            temp = mid + 1;
            while (temp < right) {
                if (elements[temp].compareTo(elements[mid]) == 0) {
                    indexList.add(temp);
                    temp++;
                } else {
                    break;
                }
            }
            return indexList;
        }
    }
}
