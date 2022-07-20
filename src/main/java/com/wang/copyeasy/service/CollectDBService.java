package com.wang.copyeasy.service;


import com.wang.copyeasy.vo.Result;
import com.wang.copyeasy.form.DBForm;

public interface CollectDBService {
     //检查是否连接成功
     Result checkCollect(DBForm form) throws Exception;

     //批量插入
     StringBuilder batchInsertSql(DBForm form);
}
