package com.yh.java8.algorithm;

/**
 * 汉诺塔问题
 *
 * @author yanhuan
 */
public class Hanoitower {

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("move first pan: " + a + "->" + c);
        } else {
            //如果有n >= 2情况，可以看做是两个盘：1.最下边的一个盘 2.上面的所有盘
            //先把最上面的所有盘从a -> b, 移动过程中会使用的c
            hanoiTower(num - 1, a, c, b);
            System.out.println(num + " from " + a + "->" + c);
            //把B塔的盘从b -> c
            hanoiTower(num - 1, b, a, c);
        }

    }
}
