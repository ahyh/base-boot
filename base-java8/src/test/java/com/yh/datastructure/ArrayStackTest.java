package com.yh.datastructure;

import com.yh.java8.datastructure.ArrayStack;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ArrayStackTest {

    @Test
    public void testStack() {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.printStack();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.printStack();
    }


}
