package com.wang.copyeasy.controller;

import com.alibaba.druid.util.StringUtils;
import com.wang.copyeasy.VO.Result;
import com.wang.copyeasy.form.DBForm;
import com.wang.copyeasy.service.CollectDBService;
import com.wang.copyeasy.utils.DBUtils;
import com.wang.copyeasy.utils.RandomSqlUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CollectDBController {

    @Autowired
    private CollectDBService collectDBService;

    @PostMapping("/checkCollect")
    public Result checkCollect(@RequestBody  DBForm form)throws Exception{
        if (Strings.isBlank(form.getPassword())){
            return new Result(false,"请检查格式","");
        }
        if (Strings.isBlank(form.getUrl())){
            return new Result(false,"请检查格式","");
        }
        if (Strings.isBlank(form.getUserName())){
            return new Result(false,"请检查格式","");
        }
        return collectDBService.checkCollect(form);
    }
    @GetMapping("/get3")
    public Result get3(DBForm form) {
        Map<String, String> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getConnection(form);

            ps = connection.prepareStatement("select COLUMN_NAME,DATA_TYPE from USER_TAB_COLUMNS where TABLE_NAME= ?");
            ps.setString(1, form.getTableName());
            rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("COLUMN_NAME"), rs.getString("DATA_TYPE"));
            }
            StringBuilder sql = new StringBuilder();
            sql.append("insert into " + form.getTableName() + " values(");//拼接sql
            StringBuilder columnName = new StringBuilder();//字段名
            StringBuilder dataType = new StringBuilder();//字段类型
            int count = 0;

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value.equalsIgnoreCase("date")) {
                    sql.append(RandomSqlUtils.getDate()).append(",");
                }
                if (value.equalsIgnoreCase("number")) {
                    sql.append(RandomSqlUtils.getNumber()).append(",");
                }
                if (value.equalsIgnoreCase("varchar2")) {
                    try {
                        Integer.valueOf(value);//可以转换则为数字
                        sql.append(RandomSqlUtils.getNumber());
                    } catch (NumberFormatException e) {//否则生成字符串
                        sql.append(RandomSqlUtils.getVarChar()).append(",");
                    }
                }
                count++;
            }
            list.add(sql);
            for (Object o : list) {
                StringBuilder o1 = (StringBuilder) o;
                int i = o1.lastIndexOf(",");
                String sql2 = o1.substring(0, i) + ")";
                list2.add(sql2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new Result(false,"连接失败","");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, ps, rs);
        }
        return new Result(true, "查询成功", list2);
    }
}
