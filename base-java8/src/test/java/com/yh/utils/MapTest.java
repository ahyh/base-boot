package com.yh.utils;

import com.yh.java8.pojo.MapKey;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    @Test
    public void testPut() {
        Map<MapKey, String> map = new HashMap<>();
        map.put(new MapKey(1), "1");
        map.put(new MapKey(2), "1");
        map.put(new MapKey(3), "1");
        map.put(new MapKey(4), "1");
        map.put(new MapKey(5), "1");
        map.put(new MapKey(6), "1");
        map.put(new MapKey(7), "1");
        map.put(new MapKey(8), "1");
        map.put(new MapKey(9), "1");
        map.put(new MapKey(10), "1");
        map.put(new MapKey(11), "1");
        map.put(new MapKey(12), "1");
        map.put(new MapKey(13), "1");
        map.put(new MapKey(14), "1");
        map.put(new MapKey(15), "1");
        map.put(new MapKey(16), "1");
        map.put(new MapKey(17), "1");
        map.put(new MapKey(18), "1");
        map.put(new MapKey(19), "1");
        map.put(new MapKey(20), "1");
        map.put(new MapKey(21), "1");
        map.put(new MapKey(22), "1");
        map.put(new MapKey(23), "1");
        map.put(new MapKey(24), "1");
        map.put(new MapKey(25), "1");
        map.put(new MapKey(26), "1");
        map.put(new MapKey(27), "1");
        map.put(new MapKey(28), "1");
        map.put(new MapKey(29), "1");
        map.put(new MapKey(30), "1");
        map.put(new MapKey(31), "1");
        map.get(new MapKey(23));
    }
}
