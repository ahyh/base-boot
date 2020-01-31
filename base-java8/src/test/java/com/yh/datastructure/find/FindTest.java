package com.yh.datastructure.find;

import com.yh.java8.datastructure.find.Find;
import org.junit.Test;

import java.util.List;

public class FindTest {

    @Test
    public void testBinarySearch() {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int i = Find.binarySearch(nums, 6);
        System.out.println(i);
    }

    @Test
    public void testBinarySearchAll() {
        Integer[] nums = {1, 2, 3, 4, 5, 5, 5, 5, 5, 6, 7, 8, 9, 10};
        List<Integer> integers = Find.binarySearchAll(nums, 5);
        System.out.println(integers.toArray().toString());
    }

    @Test
    public void testBinarySearch2(){
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int i = Find.binarySearch2(nums, 6);
        System.out.println(i);
    }
}
