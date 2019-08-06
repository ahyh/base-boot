package com.yh.boot.retrofit.demo.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User Collection：模拟数据库中的数据
 *
 * @author yanhuan
 */
public class UserCollection {

    public static final List<User> USER_LIST = new ArrayList<>();

    static {
        USER_LIST.add(new User(1L, "aa", 23, new BigDecimal(123.23), new Date()));
        USER_LIST.add(new User(2L, "bb", 24, new BigDecimal(123.23), new Date()));
        USER_LIST.add(new User(3L, "cc", 25, new BigDecimal(123.23), new Date()));
        USER_LIST.add(new User(4L, "dd", 26, new BigDecimal(123.23), new Date()));
        USER_LIST.add(new User(5L, "ee", 27, new BigDecimal(123.23), new Date()));
    }
}
