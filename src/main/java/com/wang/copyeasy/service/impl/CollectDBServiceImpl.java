package com.wang.copyeasy.service.impl;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.wang.copyeasy.VO.Result;
import com.wang.copyeasy.form.DBForm;
import com.wang.copyeasy.service.CollectDBService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

@Service
public class CollectDBServiceImpl implements CollectDBService {


    @Override
    public Result checkCollect(DBForm form)  throws Exception {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(form.getUrl(), form.getUserName(), form.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result(false,"连接失败","");
        }
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user_tables");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet);
        return new Result(true,"连接成功","");
    }
}
