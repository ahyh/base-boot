package com.yh.java8.al4.leetcode.bit;

/**
 * 求两个整数的汉明距离
 * 汉明距离就是两个整数对应二进制表示对应位不同的个数
 * eg. 1 -> 0001
 * 3 -> 0101
 * 1和3的二进制表示只有一个位不同，所以汉明距离是1
 * 利用x = x & (x -1)可以清除最低位的1
 *
 * @author yanhuan
 */
public class HanmingDistance {

    public static void main(String[] args) {
        int dis = hanmingDistance(1,3);
        System.out.println(dis);
    }

    public static int hanmingDistance(int i, int j) {
        int count = 0;
        for (int xor = i ^ j; xor !=0; xor = xor & (xor - 1)) {
            count++;
        }
        return count;
    }
}
