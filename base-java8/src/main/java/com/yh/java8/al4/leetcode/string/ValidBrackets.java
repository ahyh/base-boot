package com.yh.java8.al4.leetcode.string;

import java.util.Stack;

/**
 * 正确的括号
 * 判断括号的形式是否正确，比如()  {[()]}
 *
 * @author yanhuan
 */
public class ValidBrackets {

    public static void main(String[] args) {
        String s = "{[()]}";
        System.out.println(isValid(s));
    }

    /**
     * 判断输入的括号的形式是否正确
     * 利用栈的特性来判断括号的形式是否正确
     */
    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || c != stack.pop()) {
                    return false;
                }
            }
        }
        return true;
    }
}
