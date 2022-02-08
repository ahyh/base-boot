package com.yh.java8.al4.leetcode.list;

/**
 * 相交链表
 * 给定两个链表，假定不存在环，判断是否存在相交节点，不存在返回null，存在的话返回相交节点
 *
 * @author yanhuan
 */
public class IntersectionNode {

    public static ListNode intersectionNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        // 定义两个指针分别指向头结点
        ListNode p1 = l1, p2 = l2;
        while (p1 != p2) {
            // p1 != p2的时候，都往后挪一位，如果挪一位为null了，那么就指向另一个链表
            p1 = p1 == null ? l2 : p1.next;
            p2 = p2 == null ? l1 : p2.next;
        }
        return p1;
    }

    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node13 = new ListNode(13, null);
        ListNode node12 = new ListNode(12, node13);
        ListNode node11 = new ListNode(11, node12);

        ListNode node23 = new ListNode(23, null);
        ListNode node22 = new ListNode(22, node23);
        ListNode node21 = new ListNode(21, node22);

        // 设置相交节点
        ListNode nodeIntersection = new ListNode(31, new ListNode(32, new ListNode(33, null)));
        node13.next = nodeIntersection;
        node23.next = nodeIntersection;

        ListNode listNode = intersectionNode(node11, node21);
        System.out.println(listNode);
    }
}
