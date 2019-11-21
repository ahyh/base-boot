package com.yh.utils;

import com.yh.java8.utils.JWTUtil;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtilTest {

    @Test
    public void jwtTokenTest() {
        //获取生成token

        Map<String, Object> map = new HashMap<>();
        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", "huanyan");
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime() + 60000);
        try {
            String token = JWTUtil.createToken(map);
            System.out.println(token);
        } catch (Exception e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
    }

    @Test
    public void parseToken() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJodWFueWFuIiwic3RhIjoxNTc0MzQ5OTE3MDg2LCJleHAiOjE1NzQzNDk5NzcwODZ9.pYjoO3JA9dBP-iAkKkpAlGs4ZpIwY2QRjnFmlCYSINA";
        Map<String, Object> map = JWTUtil.valid(token);
        System.out.println(map);
    }
}
