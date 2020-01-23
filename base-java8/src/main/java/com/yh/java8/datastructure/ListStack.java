package com.yh.java8.datastructure;

/**
 * 使用链表模拟栈
 *
 * @author yanhuan
 */
public class ListStack<T> {

    private Node<T> header;

    /**
     * 初始化空栈
     */
    public ListStack() {
        header = new Node<>();
    }

    /**
     * 销毁栈
     */
    public void destory() {
        Node temp = header;
        Node next = header.getNext();
        while (next != null) {
            temp = null;
            temp = next;
            next = next.getNext();
        }
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return header.getNext() == null;
    }

    /**
     * 入栈
     */
    public void push(T data) {
        Node next = header.getNext();
        Node<T> newNode = new Node<>(data);
        newNode.setNext(next);
        header.setNext(newNode);
    }

    /**
     * 出栈
     */
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack empty");
        }
        Node first = header.getNext();
        header.setNext(first.getNext());
        return (T) first.getData();
    }

    /**
     * 获取栈顶元素
     */
    public T getTop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack empty");
        }
        Node first = header.getNext();
        return (T) first.getData();
    }

    public void printStack() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Empty");
        }
        Node curNode = header.getNext();
        while (curNode != null) {
            System.out.println(curNode.getData());
            curNode = curNode.getNext();
        }
    }

}
