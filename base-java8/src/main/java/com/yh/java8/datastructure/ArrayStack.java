package com.yh.java8.datastructure;

/**
 * 顺序栈
 *
 * @author yanhuan
 */
public class ArrayStack<T> {

    /**
     * 实际存放元素的数组
     */
    private Object[] stack;

    /**
     * 栈顶，初始化为-1
     */
    private int top = -1;

    /**
     * 栈中可以保存的最大元素数量
     */
    private int maxSize;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new Object[maxSize];
    }

    public void destory() {
        this.stack = null;
        this.top = -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(T element) {
        if (isFull()) {
            System.out.println("Stack Full");
            return;
        }
        top++;
        stack[top] = element;
    }

    /**
     * 出栈
     */
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Empty");
        }
        Object ele = stack[top];
        top--;
        return (T) ele;
    }

    /**
     * 取栈顶元素
     */
    public T getTop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Empty");
        }
        return (T) stack[top];
    }

    /**
     * 遍历栈
     */
    public void printStack() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Empty");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("Stack[" + i + "] = " + stack[i]);
        }
    }

}
