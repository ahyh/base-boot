package com.yh.java8.al4.leetcode.list;

/**
 * 链表中倒数第K个节点
 *
 * @author yanhuan
 */
public class ListLastKNode {

    public static ListNode lastKNode(ListNode node, int k) {
        if (node == null) return null;
        if (node.next == null && k == 1) return node;
        ListNode pk = node, p = node;
        // 先将pk移动k-1个位置
        for (int i = 0; i < k - 1; i++) {
            if (pk != null) {
                pk = pk.next;
            } else {
                return null;
            }
        }
        // 同时移动pk和p, 直到pk.next为null, 此时p即指向倒数第k个节点
        while (pk.next != null) {
            pk = pk.next;
            p = p.next;
        }
        return p;
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
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null))))));
        ListNode lastKNode = lastKNode(node, 3);
        System.out.println(lastKNode);
    }
}
