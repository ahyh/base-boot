package com.yh.other.test;

import org.junit.Test;

import java.util.Random;

public class MainTest {

    @Test
    public void testGetRandomInt(){
        System.out.println(new Random().nextInt(10));
    }
}
