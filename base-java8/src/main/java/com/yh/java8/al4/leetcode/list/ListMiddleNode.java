package com.yh.java8.al4.leetcode.list;

/**
 * 寻找中间节点
 * eg. 1 -> 2 -> 3, 返回2
 * eg. 1 -> 2 -> 3 -> 4, 偶数个节点，返回中间靠后的节点，即3
 *
 * @author yanhuan
 */
public class ListMiddleNode {

    public static ListNode middleNode(ListNode node) {
        if (node == null) return null;
        if (node.next == null) return node;
        ListNode fast = node, slow = node;
        // 定义一个快指针，一个慢指针
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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
        ListNode node = new ListNode(1, new ListNode(2, null));
        ListNode middleNode = middleNode(node);
        System.out.println(middleNode);
    }
}
