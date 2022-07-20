package com.wang.copyeasy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.copyeasy.domain.User;

public interface UserService extends IService<User> {
    String insertUser(User user);
}
