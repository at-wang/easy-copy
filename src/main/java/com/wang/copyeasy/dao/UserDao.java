package com.wang.copyeasy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.copyeasy.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
