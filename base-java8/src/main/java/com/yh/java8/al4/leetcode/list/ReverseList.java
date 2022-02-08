package com.yh.java8.al4.leetcode.list;

/**
 * 反转链表
 *
 * @author yanhuan
 */
public class ReverseList {

    public static ListNode reverseList(ListNode node) {
        if (node == null) return null;
        if (node.next == null) return node;
        ListNode current = node, preNode = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = preNode;
            preNode = current;
            current = next;
        }
        return preNode;
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
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode listNode = reverseList(node);
        System.out.println(listNode);
    }
}
