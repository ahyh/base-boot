package com.yh.java8.al4.leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 爬楼梯问题，一次只能走1/2层楼梯，n阶楼梯有多少种走法
 *
 * @author yanhuan
 */
public class ClimbingStairSolution {

    /**
     * 递归计算楼梯走法
     */
    public long climbingStairRecursive(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbingStairRecursive(n - 1) + climbingStairRecursive(n - 2);
    }

    public Map<Integer, Long> storeMap = new HashMap<>();

    /**
     * 使用一个Map将已经求解出来的结果保存起来，避免重复计算
     */
    public long climbingStairStoreMap(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (storeMap.get(n) != null) {
            return storeMap.get(n);
        } else {
            long result = climbingStairStoreMap(n - 1) + climbingStairStoreMap(n - 2);
            storeMap.put(n, result);
            return result;
        }
    }

    public static void main(String[] args) {
        ClimbingStairSolution solution = new ClimbingStairSolution();
        long start = System.currentTimeMillis();
//        int i = solution.climbingStairRecursive(100);
        long i = solution.climbingStairStoreMap(50);
        System.out.println(i);
        System.out.println("Cost time:" + (System.currentTimeMillis() - start));
    }


}
