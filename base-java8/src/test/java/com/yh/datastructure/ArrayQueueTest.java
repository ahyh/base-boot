package com.yh.datastructure;

import com.yh.java8.datastructure.ArrayQueue;
import org.junit.Test;

public class ArrayQueueTest {

    @Test
    public void testArrayQueue(){
        ArrayQueue arrayQueue = new ArrayQueue(10);
        System.out.println(arrayQueue.isEmpty());
        System.out.println(arrayQueue.isFull());

        arrayQueue.addElement(1);
        arrayQueue.addElement(2);
        arrayQueue.printQueue();
        arrayQueue.addElement(3);
        arrayQueue.addElement(4);
        arrayQueue.addElement(5);
        arrayQueue.printQueue();

        int queue = arrayQueue.getQueue();
//        System.out.println(queue);
        arrayQueue.printQueue();
    }
}
