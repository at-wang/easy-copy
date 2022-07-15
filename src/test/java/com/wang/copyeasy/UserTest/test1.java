package com.wang.copyeasy.UserTest;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class test1 {


    @Resource
    private DruidDataSource druidDataSource;

    @Test
    void test(){

    }

    @Test
    void test2(){

    }
}
