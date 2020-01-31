package com.yh.datastructure.sort;

import com.yh.java8.datastructure.sort.Sort;
import org.junit.Test;

import java.util.Arrays;

public class SortTest {

    @Test
    public void testBubbleSort() {
        Integer[] nums = {1, 3, 4, 9, 10};
        Sort.bubbleSort(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }

    @Test
    public void testSelectSort(){
        Integer[] nums = {1, -2, 3, -4, 6};
        Sort.selectSort(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }

    @Test
    public void testInsertSort(){
        Integer[] nums = {1, -2, 3, -4, 6};
        Sort.insertSort(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }

    @Test
    public void testInsertSortByBinarySearch(){
        Integer[] nums = {1, -2, 3, -4, 6};
        Sort.insertSortBS(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }

    @Test
    public void testShellSort(){
        Integer[] nums = {1, -2, 3, -4, 6};
        Sort.shellSort(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }

    @Test
    public void testQuickSort(){
        Integer[] nums = {1, -2, 3, -4, 6};
        Sort.quickSort(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }

    @Test
    public void testMergeSort(){
        Integer[] nums = {1, -2, 3, -4, 6};
        Sort.mergeSort(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }

    @Test
    public void testMergeSortRecurse(){
        Integer[] nums = {1, -2, 3, -4, 6};
        Sort.mergeSortRecurse(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }

    @Test
    public void testHeapSort(){
        Integer[] nums = {1, -2, 3, -4, 6};
        Sort.heapSort(nums);
        Arrays.asList(nums).forEach(System.out::println);
    }
}
