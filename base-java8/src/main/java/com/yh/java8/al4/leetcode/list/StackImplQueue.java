package com.yh.java8.al4.leetcode.list;

import java.util.Stack;

/**
 * 用栈来实现队列
 *
 * @author yanhuan
 */
public class StackImplQueue {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public StackImplQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int value) {
        inStack.push(value);
    }

    public Integer pop() {
        if (outStack.isEmpty()) {
            in2Out();
        }
        return outStack.pop();
    }

    public Integer peek() {
        if (outStack.isEmpty()) {
            in2Out();
        }
        return outStack.peek();
    }

    public void in2Out() {
        while (!inStack.isEmpty()) {
            Integer pop = inStack.pop();
            outStack.push(pop);
        }
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public static void main(String[] args) {
        StackImplQueue stackImplQueue = new StackImplQueue();
        stackImplQueue.push(1);
        stackImplQueue.push(2);
        stackImplQueue.push(3);

        Integer pop = stackImplQueue.pop();
        System.out.println(pop);

        pop = stackImplQueue.pop();
        System.out.println(pop);

        stackImplQueue.push(4);

        System.out.println();
    }
}
