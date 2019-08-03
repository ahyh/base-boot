package com.yh.boot.mybatis.demo.test;

import com.yh.boot.mybatis.source.code.dao.UserMapper;
import com.yh.boot.mybatis.source.code.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

public class MybatisSourceTest {

    @Test
    public void test() throws Exception {
        //0-mybatis全局配置文件路径
        String cfgPath = "mybatis/mybatis-config.xml";
        //1-读取配置文件，获得SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(cfgPath));
        //2-通过SqlSessionFactory对象获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3-获取Mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //4-执行SQL语句，获取ORM对象
        User user = userMapper.getUserById(1L);
        System.out.println(user);
        Assert.assertTrue(user != null);
    }
}
