package com.yh;


import com.yh.bytes.asm.AsmDemo;
import org.junit.Test;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testGetBytes() throws Exception {
        byte[] bytes = AsmDemo.getBytes("D://file//MyDemo.class");
        System.out.println(bytes);
    }
}
