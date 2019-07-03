package com.yh.java8.forkjoinpool;

import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool任务
 *
 * @author yanhuan
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] nums;

    private final int start;

    private final int end;

    //切分任务的阈值
    private static final long THRESHOLD = 10000;

    public ForkJoinSumCalculator(long[] nums) {
        this(nums, 0, nums.length);
    }

    private ForkJoinSumCalculator(long[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        //如果数组的大小小于阈值则直接计算返回结果
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        //创建一个子任务：数组的前一半进行求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(nums, start, start + length / 2);
        //利用另一个ForkJoinPool线程来异步执行新创建的任务
        leftTask.fork();
        //创建一个子任务：数组的后一半进行求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(nums, start + length / 2, end);
        Long rightValue = rightTask.compute();
        Long leftValue = leftTask.join();
        return leftValue + rightValue;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
