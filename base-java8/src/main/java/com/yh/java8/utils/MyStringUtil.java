package com.yh.java8.utils;

import com.google.common.base.Preconditions;
import com.yh.java8.datastructure.ArrayStack;
import com.yh.java8.datastructure.ListStack;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * String相关工具
 *
 * @author yanhuan
 */
public class MyStringUtil {

    private static final Map<Character, Integer> LEFT_PRI = new HashMap<>();
    private static final Map<Character, Integer> RIGHT_PRI = new HashMap<>();

    /**
     * 设置优先级
     */
    static {
        LEFT_PRI.put('=', 0);
        LEFT_PRI.put('(', 1);
        LEFT_PRI.put('*', 5);
        LEFT_PRI.put('/', 5);
        LEFT_PRI.put('+', 3);
        LEFT_PRI.put('-', 3);
        LEFT_PRI.put(')', 6);

        RIGHT_PRI.put('=', 0);
        RIGHT_PRI.put('(', 6);
        RIGHT_PRI.put('*', 4);
        RIGHT_PRI.put('/', 4);
        RIGHT_PRI.put('+', 2);
        RIGHT_PRI.put('-', 2);
        RIGHT_PRI.put(')', 1);
    }

    /**
     * 利用栈判断str是否是对称串
     *
     * @param str 入参
     * @return 是否是对称串
     */
    public static boolean checkStringSymmetry(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        ArrayStack<Character> arrayStack = new ArrayStack<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            arrayStack.push(str.charAt(i));
        }
        for (int i = 0; i < str.length(); i++) {
            if (!arrayStack.pop().equals(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断输入的字符串中括号是否成对出现
     */
    public static boolean isBracketPair(String exp) {
        if (StringUtils.isBlank(exp)) {
            return false;
        }
        ListStack<Character> listStack = new ListStack<>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if ('(' == c) {
                listStack.push(c);
            }
            if (')' == c) {
                if (listStack.isEmpty()) {
                    return false;
                }
                Character pop = listStack.pop();
                if ('(' != pop) {
                    return false;
                }
            }
        }
        return true;
    }


    private static int leftPri(char op) {
        return LEFT_PRI.get(op);
    }

    private static int rightPri(char op) {
        return RIGHT_PRI.get(op);
    }

    private static boolean isOp(char ch) {
        return LEFT_PRI.containsKey(ch);
    }

    private static int compareOp(char left, char right) {
        if (LEFT_PRI.get(left).equals(RIGHT_PRI.get(right))) {
            return 0;
        }
        if (LEFT_PRI.get(left) < RIGHT_PRI.get(right)) {
            return -1;
        }
        return 1;
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param exp 中缀表达式
     * @return 后缀表达式
     */
    public static String transMiddle2PostExp(String exp) {
        //初始化操作符栈
        ListStack<Character> opStack = new ListStack<>();
        opStack.push('=');
        //初始化后缀表达式
        StringBuilder postExp = new StringBuilder();
        int i = 0;
        while (i < exp.length()) {
            char curChar = exp.charAt(i);
            if (curChar == ' ') {
                i++;
                continue;
            }
            if (!isOp(curChar)) {
                while (i < exp.length() && !isOp(exp.charAt(i))) {
                    postExp.append(exp.charAt(i));
                    i++;
                }
                postExp.append(' ');
            } else {
                switch (compareOp(opStack.getTop(), curChar)) {
                    case -1:
                        opStack.push(curChar);
                        i++;
                        break;
                    case 0:
                        opStack.pop();
                        i++;
                        break;
                    case 1:
                        postExp.append(opStack.pop()).append(' ');
                        break;
                }
            }
        }
        while (opStack.getTop() != '=') {
            postExp.append(opStack.pop());
        }
        return postExp.toString();
    }

    /**
     * 计算后缀表达式的值
     *
     * @param postExp 后缀表达式
     * @return 后缀表达式计算结果
     */
    public static int computePostExp(String postExp) {
        if (StringUtils.isBlank(postExp)) {
            throw new RuntimeException("Empty post exp");
        }
        ListStack<Integer> stack = new ListStack<>();
        int i = 0, a, b, d;
        while (i < postExp.length()) {
            switch (postExp.charAt(i)) {
                case ' ':
                    break;
                case '+':
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(a + b);
                    break;
                case '-':
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b - a);
                    break;
                case '*':
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(a * b);
                    break;
                case '/':
                    a = stack.pop();
                    b = stack.pop();
                    if (a != 0) {
                        stack.push(b / a);
                    } else {
                        throw new RuntimeException("can not divide zero");
                    }
                    break;
                default:
                    d = 0;
                    while (!isOp(postExp.charAt(i)) && postExp.charAt(i) != ' ') {
                        d = d * 10 + postExp.charAt(i) - '0';
                        i++;
                    }
                    stack.push(d);
                    break;
            }
            i++;
        }
        return stack.pop();
    }

    /**
     * kmp算法求解模式串的位置
     *
     * @param sourceStr 源串
     * @param subStr    模式串
     * @return 模式串的位置
     */
    public static int kmpIndex(String sourceStr, String subStr) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sourceStr), "source string should not empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(subStr), "sub string should not empty");
        int[] next = getNext(subStr);
        int i = 0, j = 0;
        while (i < sourceStr.length() && j < subStr.length()) {
            if (j == -1 || sourceStr.charAt(i) == subStr.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= subStr.length()) {
            return i - subStr.length();
        } else {
            return -1;
        }
    }

    /**
     * 获取模式串的next数组
     *
     * @param subStr 待查找的子串
     * @return next数组
     */
    private static int[] getNext(String subStr) {
        int[] next = new int[subStr.length()];
        next[0] = -1;
        int i, k = -1;
        for (i = 0; i < subStr.length() - 1; ) {
            if (k == -1 || subStr.charAt(i) == subStr.charAt(k)) {
                i++;
                k++;
                next[i] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int i = kmpIndex("abcabceraea", "cab");
        System.out.println(i);
    }
}
