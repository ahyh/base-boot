package com.yh.datastructure;

import com.yh.java8.datastructure.ListStack;
import org.junit.Test;

public class ListStackTest {

    @Test
    public void testListStack() {
        ListStack<Integer> listStack = new ListStack<>();
        listStack.push(1);
        listStack.push(2);
        listStack.push(3);
        listStack.push(4);
        listStack.push(5);

        System.out.println(listStack.pop());
        System.out.println(listStack.getTop());

        listStack.printStack();
    }
}
