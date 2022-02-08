package com.yh.java8.al4.leetcode.list;

/**
 * 合并两个有序链表
 *
 * @author yanhuan
 */
public class MergeTwoLists {

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode resultNode = new ListNode(0);
        ListNode p = resultNode;
        // 使用双指针的方法
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 == null) {
            p.next = l2;
        }
        if (l2 == null) {
            p.next = l1;
        }
        return resultNode.next;
    }

    public static class ListNode {
        protected int value;
        protected ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(5, null)));
        ListNode listNode = mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }
}
