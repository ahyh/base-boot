package com.yh.boot.mybatis.source.code.main;

import com.yh.boot.mybatis.source.code.dao.UserDao;
import com.yh.boot.mybatis.source.code.model.User;
import com.yh.boot.mybatis.source.code.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * Learn Mybatis，For source code
 *
 * @author yanhuan
 */
public class MybatisDemo {

    private static final String CFG_FILE = "mybatis/mybatis-config.xml";

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession(CFG_FILE);
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.getUserById(6L);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
