package com.yh.java8.datastructure.problem;

import com.yh.java8.datastructure.CircleLinkedList;
import com.yh.java8.datastructure.Node;

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
    public static void josephusByArray(int n, int m, int k) {
        checkParams(n, m, k);
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        int t = 0;
        for (int i = n; i >= 1; i--) {
            t = (t + m + k - 2) % i;
            System.out.println("移除的节点: " + nums[t]);
            for (int j = t + 1; j <= i - 1; j++) {
                nums[j - 1] = nums[j];
            }
        }
    }

    /**
     * 求解约瑟夫问题
     *
     * @param n 总人数
     * @param m 需要出列的编号
     * @param k 从第k个开始数
     * @return 约瑟夫问题解
     */
    public static void josephusByCircleList(int n, int m, int k) {
        checkParams(n, m, k);
        Integer[] nums = new Integer[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        CircleLinkedList<Integer> list = new CircleLinkedList(nums, false);
        //helper指向最后一个节点, 即first的上一个节点
        Node helper = list.getFirst();
        while (true) {
            if (helper.getNext() == list.getFirst()) {
                break;
            }
            helper = helper.getNext();
        }

        for (int i = 0; i < k - 1; i++) {
            list.setFirst(list.getFirst().getNext());
            helper = helper.getNext();
        }
        while (true) {
            if (helper == list.getFirst()) {
                break;
            }
            for (int j = 0; j < m - 1; j++) {
                //first指向的节点就是要出圈的节点
                list.setFirst(list.getFirst().getNext());
                helper = helper.getNext();
            }
            System.out.println("移除的节点：" + list.getFirst());

            list.setFirst(list.getFirst().getNext());
            helper.setNext(list.getFirst());
        }
        System.out.println("最后的节点：" + list.getFirst());
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
