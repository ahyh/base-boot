package com.yh.java8.datastructure.problem;

import org.apache.commons.lang3.StringUtils;

/**
 * 递归求解相关问题
 *
 * @author yanhuan
 */
public class RecurseProblem {

    /**
     * 通过递归的方法获取字符串的反转串
     *
     * @param sourceStr 源串
     * @return 反转串
     */
    public static String invert(String sourceStr) {
        if (StringUtils.isBlank(sourceStr)) {
            return sourceStr;
        }
        String s1, s2;
        if (sourceStr.length() > 0) {
            s1 = invert(sourceStr.substring(1));
            s2 = s1 + sourceStr.substring(0, 1);
        } else {
            s2 = sourceStr;
        }
        return s2;
    }

    /**
     * 求解int[]数组中最大值
     *
     * @param nums 待求解最大值的整数数组
     * @return 数组中最大值
     */
    public static int max(int[] nums, int i, int j) {
        int max, maxl, maxr;
        if (i == j) {
            max = nums[i];
        } else {
            int mid = (i + j) / 2;
            maxl = max(nums, i, mid);
            maxr = max(nums, mid + 1, j);
            max = (maxl > maxr) ? maxl : maxr;
        }
        return max;
    }

    /**
     * 使用递归的方法
     *
     * @param nums
     * @param i
     * @param j
     * @return
     */
    public static int avg(int[] nums, int i, int j) {
        int avg, avg1, avg2;
        if (i == j) {
            avg = nums[i];
        } else {
            int mid = (i + j) / 2;
            avg1 = avg(nums, i, mid);
            avg2 = avg(nums, mid + 1, j);
            avg = (avg1 + avg2) / 2;
        }
        return avg;
    }

    private static int[] qarr = new int[20];
    private static int count = 0;

    public static void print(int n) {
        int i;
        count++;
        System.out.print(String.format("第%s个解: ", count));
        for (i = 1; i <= n; i++) {
            System.out.print(qarr[i] + " ");
        }
        System.out.println();
    }

    public static int find(int i, int k) {
        int j = 1;
        while (j < k) {
            if (qarr[j] == i || Math.abs(qarr[j] - i) == Math.abs(j - k)) {
                return 0;
            }
            j++;
        }
        return 1;
    }

    /**
     * 求解8皇后问题，在n*n的棋盘上
     *
     * @param n 棋盘的行数和列数
     */
    public static void place(int k, int n) {
        if (k > n) {
            print(n);
        } else {
            for (int i = 1; i <= n; i++) {
                if (find(i, k) == 1) {
                    qarr[k] = i;
                    place(k + 1, n);
                }
            }
        }
    }

    private static int maxValue = 0;
    private static int n = 5;
    private static int weight = 9;
    private static int[] w = {1, 2, 3, 4, 5};
    private static int[] v = {5, 4, 3, 2, 1};

    public static void dfs(int sum, int nowWeight, int step) {
        //如果当前重量超过限制，则退出
        if (nowWeight > weight) {
            return;
        }
        //已经完成n件物品的选择
        if (step == n) {
            if (sum > maxValue) {
                //更新最大价值
                maxValue = sum;
            }
            return;
        }
        //选这件物品
        dfs(sum + v[step], nowWeight + w[step], step + 1);
        dfs(sum, nowWeight, step + 1);
    }

    public static void main(String[] args) {
        dfs(0, 0, 0);
        System.out.println(maxValue);
    }

}
