package com.yh.java8.al4.leetcode.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含n个整数的数组nums, 其中nums[i]的区间在[1,n]中，
 * 找出所有在[1,n]之间没有出现的数字
 *
 * @author yanhuan
 */
public class FindDisappearedNum {

    /**
     * 相当于借助于下标来处理的
     */
    public static List<Integer> findDisappearedNumber(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = findDisappearedNumber(array);
        System.out.println(result);
    }
}
