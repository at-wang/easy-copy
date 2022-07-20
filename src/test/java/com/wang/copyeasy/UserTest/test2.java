package com.wang.copyeasy.UserTest;

import com.wang.copyeasy.dao.UserDao;
import com.wang.copyeasy.utils.RandomSqlUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
public class test2 {

    @Autowired
    UserDao userDao;

    @Test
    void test1() {
        Set<Object> set1 = new HashSet<>();
        Set<Object> set2 = new HashSet<>();
        Set<Object> set3 = new HashSet<>();
        Set<Object> set4 = new HashSet<>();
        Set<Object> set5 = new HashSet<>();
        for (int i = 0; i < 10009; i++) {
            String stringDate = RandomSqlUtils.getStringDate();
            int threeNumber = RandomSqlUtils.getFourNumber();
            String varchar3 = RandomSqlUtils.getVarchar(3);
            String varchar8 = RandomSqlUtils.getVarchar(8);
            String varchar32 = RandomSqlUtils.getVarchar(32);

            set2.add(stringDate);
            set1.add(threeNumber);
            set3.add(varchar3);
            set4.add(varchar32);
            set5.add(varchar8);

        }
        System.out.println("set2----------" + set2.size());
        System.out.println("set1----------" + set1.size());
        System.out.println("set3----------" + set3.size());
        System.out.println("set4----------" + set4.size());
        System.out.println("set5----------" + set5.size());
        System.out.println(UUID.randomUUID().toString().length());
        System.out.println(RandomSqlUtils.getVarchar(32));
        System.out.println(RandomSqlUtils.getVarchar(3));
        System.out.println(RandomSqlUtils.getVarchar(8));
        System.out.println(RandomSqlUtils.getVarchar(34).length());
    }

    @Test
    void test3(){
        System.out.println(userDao.insertUser(null));
    }
}
