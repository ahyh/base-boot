package com.yh.boot.mybatis.source.code.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * SqlSessionFactory tool class
 *
 * @author yanhuan
 */
public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory = null;

    private static final Class LOCK = SqlSessionFactoryUtil.class;

    private SqlSessionFactoryUtil() {

    }

    public static SqlSessionFactory initSqlSessionFactory(String cfg) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(cfg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession(String cfg) {
        if (sqlSessionFactory == null) {
            initSqlSessionFactory(cfg);
        }
        return sqlSessionFactory.openSession();
    }
}
