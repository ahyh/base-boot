package com.yh.utils;

import com.yh.java8.utils.SnowFlake;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SnowFlakeTest {

    @Test
    public void testNextId() throws InterruptedException {
        for (int i = 0; i < 31; i++) {
            int finalI = i;
            new Thread(() -> {
                SnowFlake snowFlake = new SnowFlake(Long.parseLong(String.valueOf(finalI)), Long.parseLong(String.valueOf(finalI)));
                for (int i1 = 0; i1 < 3000; i1++) {
                    System.out.println(Thread.currentThread().getName() + snowFlake.nextId());
                }
            }, "Thread-" + finalI).start();
        }
        TimeUnit.SECONDS.sleep(10000);
    }


}
