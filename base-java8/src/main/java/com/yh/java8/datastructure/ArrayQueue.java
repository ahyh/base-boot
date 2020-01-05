package com.yh.java8.datastructure;

/**
 * 使用数组模拟环形队列
 *
 * @author yanhuan
 */
public class ArrayQueue {

    /**
     * 队列大小
     */
    private int maxSize;

    /**
     * 指向第一个元素
     */
    private int head;

    /**
     * 指向最后一个元素的后一个位置
     */
    private int tail;

    /**
     * 实际数组
     */
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        head = 0;
        tail = 0;
    }

    /**
     * @return is queue full
     */
    public boolean isFull() {
        return (tail + 1) % maxSize == head;
    }

    /**
     * @return is queue empty
     */
    public boolean isEmpty() {
        return head == tail;
    }

    public void addElement(int ele) {
        if (isFull()) {
            throw new RuntimeException("ArrayQueue is full");
        }
        arr[tail] = ele;
        tail = (tail + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("ArrayQueue is empty");
        }
        int temp = arr[head];
        head = (head + 1) % maxSize;
        return temp;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("ArrayQueue is Empty");
        }
        for (int i = head; i < head + size(); i++) {
            System.out.println(arr[i]);
        }
    }

    public int size() {
        return (tail + maxSize - head) % maxSize;
    }

    public int showHead() {
        if (isEmpty()) {
            throw new RuntimeException("ArrayQueue is Empty");
        }
        return arr[head];
    }
}
