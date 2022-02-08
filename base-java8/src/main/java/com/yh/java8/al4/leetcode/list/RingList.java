package com.yh.java8.al4.leetcode.list;

/**
 * 给定一个链表，判断链表中是否存在环
 *
 * @author yanhuan
 */
public class RingList {

    /**
     * 引入一个块指针，一个慢指针，快指针一次移动两个节点，慢指针一次移动一个节点，如果快指针和慢指针指到
     * 相同的节点，则说明有环
     * Floyd算法
     */
    public static boolean hasCycle(ListNode node) {
        if (node == null) return false;
        ListNode fastNode = node;
        ListNode slowNode = node;
        while (slowNode.next != null && fastNode.next.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (fastNode == slowNode) {
                return true;
            }
        }
        return false;
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
        ListNode node1 = new ListNode(3, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(0, null);
        ListNode node4 = new ListNode(4, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        boolean b = hasCycle(node1);
        System.out.println(b);
    }

}
