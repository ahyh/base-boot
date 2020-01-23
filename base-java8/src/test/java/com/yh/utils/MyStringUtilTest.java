package com.yh.utils;

import com.yh.java8.utils.MyStringUtil;
import org.junit.Test;

public class MyStringUtilTest {

    @Test
    public void testCheckStringSymmetry() {
        boolean flag = MyStringUtil.checkStringSymmetry("abcddcba");
        System.out.println(flag);
    }

    @Test
    public void testCheckBracketPair(){
        boolean bracketPair = MyStringUtil.isBracketPair("((1+2) * 3) / 4 + 2");
        boolean bracketPair1 = MyStringUtil.isBracketPair("12)3");
        System.out.println(bracketPair);
        System.out.println(bracketPair1);
    }

    @Test
    public void testPost(){
        String str = "((11+21) * 3) / 41 + 2";
        String s = MyStringUtil.transMiddle2PostExp(str);
        int result = MyStringUtil.computePostExp(s);
        System.out.println(result);
        System.out.println(s);
    }
}
