package com.yh.singleton;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {

    @Test
    public void testSingleton1() {
        Singleton1 s1 = Singleton1.getSingleton();
        Singleton1 s2 = Singleton1.getSingleton();
        Assert.assertTrue(s1 == s2);
    }

    @Test
    public void testSingleton2() {
        Singleton1 s1 = Singleton1.getSingleton();
        Singleton1 s2 = Singleton1.getSingleton();
        Assert.assertTrue(s1 == s2);
    }

    @Test
    public void testSingleton3() {
        Singleton3 s1 = Singleton3.getSingleton();
        Singleton3 s2 = Singleton3.getSingleton();
        Assert.assertTrue(s1 == s2);
    }

    @Test
    public void testSingleton4() {
        Singleton4 s1 = Singleton4.getSingleton();
        Singleton4 s2 = Singleton4.getSingleton();
        Assert.assertTrue(s1 == s2);
    }

    @Test
    public void testSingleton5() {
        Singleton5 s1 = Singleton5.INSTANCE;
        Singleton5 s2 = Singleton5.INSTANCE;
        Assert.assertTrue(s1 == s2);
    }
}
