package com.yh.java8.al4.leetcode.tree;

import java.util.Stack;

public class BackorderLoopTree {

    public static void main(String[] args) {
        TreeNode<String> root = TreeNode.initStrTree();
        backorder(root);
        System.out.println();
        System.out.println("=============");
        backorderLoop(root);
        System.out.println();
    }

    /**
     * 递归的方式后序遍历二叉树
     * 结果：GHDBIEFCA
     */
    private static void backorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        backorder(treeNode.left);
        backorder(treeNode.right);
        System.out.print(treeNode.value);
    }

    /**
     * 循环实现二叉树的后序遍历，借助于栈来实现
     */
    private static void backorderLoop(TreeNode<String> treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode root = treeNode;
        TreeNode preAccess = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == preAccess) {
                System.out.print(root.value);
                preAccess = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
    }
}
