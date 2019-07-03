package com.yh.java8.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * ForkJoinPool使用案例
 *
 * @author yanhuan
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) {
        long sum = forkJoinSum(1000000);
        System.out.println(sum);
    }

    private static long forkJoinSum(long n) {
        long[] nums = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(nums);
        return new ForkJoinPool().invoke(forkJoinSumCalculator);
    }
}
