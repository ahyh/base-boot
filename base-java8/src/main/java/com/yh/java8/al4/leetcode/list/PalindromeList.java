package com.yh.java8.al4.leetcode.list;

/**
 * 回文链表
 */
public class PalindromeList {

    public static boolean isPalindromeList(ListNode node) {
        if (node == null) return false;
        if (node.next == null) return true;
        ListNode fast = node, slow = node;
        // 快指针每次移动两位，慢指针每次移动一位，直到快指针到达尾部
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 判断是奇数还是偶数个节点，如果是奇数个，慢指针往后移动一位
        if (fast != null) {
            slow = slow.next;
        }
        // 反转
        slow = reverseList(slow);
        fast = node;
        while (slow != null) {
            // 遍历比较
            if (slow.value != fast.value) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    private static ListNode reverseList(ListNode node) {
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
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1, null))));
        boolean palindromeList = isPalindromeList(node);
        System.out.println(palindromeList);
    }
}
