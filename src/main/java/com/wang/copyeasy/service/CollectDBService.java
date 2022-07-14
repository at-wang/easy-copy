package com.wang.copyeasy.service;


import com.wang.copyeasy.VO.Result;
import com.wang.copyeasy.form.DBForm;
import com.wang.copyeasy.utils.DBUtils;

public interface CollectDBService {
     Result checkCollect(DBForm form) throws Exception;
}
