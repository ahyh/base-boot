package com.yh.spring.demo.test.utils;

import com.yh.spring.demo.common.utils.ReflectUtil;
import com.yh.spring.demo.mapper.UserMapper;
import com.yh.spring.demo.pojo.UserQuery;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

public class ReflectUtilTest {

    @Test
    public void testGetGenericClasses() throws NoSuchMethodException {
        Class<UserMapper> userMapperClass = UserMapper.class;
        Method method = userMapperClass.getMethod("findUserList", UserQuery.class);
        List<Class<?>> classList = ReflectUtil.getReturnGenericTypeByMethod(method);
        System.out.println(classList);
    }
}
