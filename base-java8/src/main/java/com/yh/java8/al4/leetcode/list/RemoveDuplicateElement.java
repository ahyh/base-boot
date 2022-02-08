package com.yh.java8.al4.leetcode.list;

/**
 * 删除升序链表中的重复元素
 * eg. 1 -> 1 -> 2  =>>  1 -> 2
 *
 * @author yanhuan
 */
public class RemoveDuplicateElement {

    private static ListNode removeDuplicateElement(ListNode listNode) {
        if (listNode == null) return null;
        ListNode p = listNode;
        while (p.next != null) {
            if (p.value == p.next.value) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return listNode;
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
        ListNode listNode = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, null))))));
        ListNode listNode1 = removeDuplicateElement(listNode);
        System.out.println(listNode1);
    }
}
