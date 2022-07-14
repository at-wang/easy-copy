package com.wang.copyeasy.UserTest;

import com.alibaba.druid.pool.DruidDataSource;
import com.wang.copyeasy.dao.UserDao;
import com.wang.copyeasy.dao.UserDao2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@SpringBootTest
public class test1 {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDao2 userDao2;

    @Resource
    private DruidDataSource druidDataSource;

    @Test
    void test(){
        System.out.println(userDao.selectById(1));
        try {
            druidDataSource.restart();
            druidDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
            druidDataSource.setUsername("c##wang");
            druidDataSource.setPassword("wang");
            druidDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            System.out.println(userDao.selectById(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {

        }
    }

    @Test
    void test2(){
        System.out.println(userDao.selectById(1));
        try {
            druidDataSource.restart();
            druidDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1522:ora11g");
            druidDataSource.setUsername("wang3");
            druidDataSource.setPassword("wang3");
            druidDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            System.out.println(userDao2.selectById(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
