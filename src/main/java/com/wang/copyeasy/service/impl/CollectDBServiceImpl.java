package com.wang.copyeasy.service.impl;

import com.wang.copyeasy.vo.Result;
import com.wang.copyeasy.domain.DataBase;
import com.wang.copyeasy.form.DBForm;
import com.wang.copyeasy.service.CollectDBService;
import com.wang.copyeasy.utils.DBUtils;
import com.wang.copyeasy.utils.RandomSqlUtils;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectDBServiceImpl implements CollectDBService {


    @Override
    public Result checkCollect(DBForm form) throws Exception {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            DriverManager.getConnection(form.getUrl(), form.getUserName(), form.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result(false, "连接失败", "");
        }
        return new Result(true, "连接成功", "");
    }

    /**
     * 生成批量sql
     *
     * @param form
     * @return
     */
    @Override
    public StringBuilder batchInsertSql(DBForm form) {
        List<DataBase> dBList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getConnection(form);

            ps = connection.prepareStatement("select COLUMN_NAME,DATA_TYPE,DATA_LENGTH from USER_TAB_COLUMNS where TABLE_NAME= ?");
            ps.setString(1, form.getTableName());
            rs = ps.executeQuery();
            while (rs.next()) {
                DataBase dataBase = new DataBase(rs.getString("COLUMN_NAME"),
                        rs.getString("DATA_TYPE"), rs.getInt("DATA_LENGTH"));
                dBList.add(dataBase);
            }

            sql.append("insert into ").append(form.getTableName()).append(" values(");//拼接sql
            for (DataBase dataBase : dBList) {
                if (dataBase.getDataType().equalsIgnoreCase("date")) {
                    sql.append(RandomSqlUtils.getDate()).append(",");
                }
                if (dataBase.getDataType().equalsIgnoreCase("number")) {
                    sql.append(RandomSqlUtils.getNumber()).append(",");
                }
                if (dataBase.getDataType().equalsIgnoreCase("varchar2")) {
                    if (dataBase.getDataLength() < 4) {
                        sql.append(RandomSqlUtils.getNumber()).append(",");
                    } else {
                        sql.append(RandomSqlUtils.getVarChar()).append(",");
                    }


                }

            }

        } catch (SQLException e) {
            return new StringBuilder("失败");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, ps, rs);
        }
        return sql;
    }
}
