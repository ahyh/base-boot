package com.yh.java8.datastructure.problem;

/**
 * 约瑟夫问题
 * <p>
 * 有n个人排成一圈，编号为1-n，从编号为m的人开始报数，数到k的人出列，然后从出列者下一个人
 * 重新开始报数，数到k的出列如此循环，直到n个都出列为止
 *
 * @author yanhuan
 */
public class JosephusProblem {

    /**
     * 求解约瑟夫问题
     *
     * @param n 总人数
     * @param m 需要出列的编号
     * @param k 从第k个开始数
     * @return 约瑟夫问题解
     */
    public static int[] josephus1(int n, int m, int k) {
        checkParams(n, m, k);
        return null;
    }

    private static void checkParams(int n, int m, int k) {
        if (n < 0 || m < 0 || k < 0) {
            throw new RuntimeException("param cannot less 0");
        }
        if (n == 0 || k < 1 || m > n) {
            throw new RuntimeException("params invalid");
        }
    }
}
