package com.yh.java8.al4.leetcode.tree;

import java.util.Stack;

public class FrontorderLoopTree {

    public static void main(String[] args) {
        TreeNode<String> root = TreeNode.initStrTree();
        frontorder(root);
        System.out.println();
        System.out.println("=============");
        frontorderLoop(root);
        System.out.println();
        System.out.println();
    }

    /**
     * 递归的方式前序遍历二叉树
     * 结果：ABDGHCEIF
     */
    private static void frontorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.value);
        frontorder(treeNode.left);
        frontorder(treeNode.right);
    }

    /**
     * 循环实现二叉树的前序遍历，借助于栈来实现
     */
    private static void frontorderLoop(TreeNode<String> treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode root = treeNode;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                System.out.print(root.value);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
    }
}
