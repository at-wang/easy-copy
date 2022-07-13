package com.wang.copyeasy.UserTest;

import com.alibaba.druid.pool.DruidDataSource;
import com.wang.copyeasy.dao.UserDao;
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

    @Resource
    private DruidDataSource druidDataSource;

    @Test
    void test(){
        System.out.println(userDao.selectById(1));
        try {
            //ruidDataSource.restart(); 需要restart否则会包UnsupportedOperationException
            druidDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
            druidDataSource.setUsername("c##wang");
            druidDataSource.setPassword("wang");
            druidDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            System.out.println(userDao.selectById(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
