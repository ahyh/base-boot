package com.yh.java8.al4.leetcode.tree;

/**
 * 判读一颗二叉树是否是对称的
 *
 * @author yanhuan
 */
public class SymmetricTreeCheck {

    public static void main(String[] args) {
        TreeNode node = TreeNode.initIntTree();
        boolean check = check(node);
        System.out.println(check);
    }

    public static boolean check(TreeNode<Integer> node) {
        if (node == null) {
            return false;
        }
        return deepCheck(node.left, node.right);
    }

    private static boolean deepCheck(TreeNode<Integer> left, TreeNode<Integer> right) {
        // 递归的终止条件是左右子树有一个为null
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        // 两个节点的只不相等，直接返回false
        if (!left.value.equals(right.value)) {
            return false;
        }
        // 递归调用左子树的left和右子树的right, 以及左子树的right和右子树的left
        return deepCheck(left.left, right.right) && deepCheck(left.right, right.left);
    }
}
