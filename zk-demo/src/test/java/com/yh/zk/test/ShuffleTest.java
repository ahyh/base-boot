package com.yh.zk.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 测试Collections中工具方法
 *
 * @author yanhuan
 */
public class ShuffleTest {

    @Test
    public void testShuffle() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        list.forEach(System.out::println);
        //将集合中元素的顺序打乱
        Collections.shuffle(list);
        list.forEach(System.out::println);
    }
}
