package com.wang.copyeasy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.copyeasy.dao.UserDao;
import com.wang.copyeasy.domain.User;
import com.wang.copyeasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public String insertUser(User user) {
        int insert = userDao.insert(user);
        if (insert>0){
            return "成功";
        }
        return "失败";
    }
}
