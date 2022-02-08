package com.yh.java8.al4.leetcode.tree;

import java.util.Stack;

public class InorderLoopTree {

    public static void main(String[] args) {
        TreeNode<String> root = TreeNode.initStrTree();
        inorder(root);
        System.out.println();
        System.out.println("=============");
        inorderLoop(root);
        System.out.println();
        System.out.println();
    }

    /**
     * 递归的方式中序遍历二叉树
     * 结果：GDHBAEICF
     */
    private static void inorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inorder(treeNode.left);
        System.out.print(treeNode.value);
        inorder(treeNode.right);
    }

    /**
     * 循环实现二叉树的中序遍历，借助于栈来实现
     */
    private static void inorderLoop(TreeNode<String> treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode root = treeNode;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.value);
            root = root.right;
        }
    }
}
