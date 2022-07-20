package com.wang.copyeasy.service.impl;

import com.wang.copyeasy.domain.DataBase;
import com.wang.copyeasy.form.DBForm;
import com.wang.copyeasy.service.EasyRequestService;
import com.wang.copyeasy.utils.DBUtils;
import com.wang.copyeasy.utils.RandomSqlUtils;
import com.wang.copyeasy.vo.Result;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class EasyRequestServiceImpl implements EasyRequestService {


    @Override
    public Result queryFormParams(DBForm dbForm) {
        List<DataBase> formList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtils.getConnection(dbForm);
            String sql="select t.COLUMN_NAME, DATA_TYPE, DATA_LENGTH, c.COMMENTS from USER_TAB_COLUMNS t inner join USER_COL_COMMENTS c on t.COLUMN_NAME = c.COLUMN_NAME where t.TABLE_NAME = ? and c.TABLE_NAME = ? order by COLUMN_ID";
            ps = connection.prepareStatement(sql);
            ps.setString(1, dbForm.getTableName());
            ps.setString(2, dbForm.getTableName());
            rs = ps.executeQuery();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String dataType = rs.getString("DATA_TYPE");
                int dataLength = rs.getInt("DATA_LENGTH");
                String comments = rs.getString("COMMENTS");
                DataBase dataBase = new DataBase(columnName,
                        dataType, dataLength, comments);
                if (dataType.equalsIgnoreCase("varchar2")){
                    String varchar = RandomSqlUtils.getVarchar(dataLength);
                    dataBase.setData(varchar);
                }else if (dataType.equalsIgnoreCase("number")){
                    int fourNumber = RandomSqlUtils.getFourNumber();
                    dataBase.setData(String.valueOf(fourNumber));
                }else if (dataType.equalsIgnoreCase("date")){
                    dataBase.setData(RandomSqlUtils.getStringDate());
                }

                formList.add(dataBase);
            }



            return new Result(true,"查询成功",formList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Result(false,"查询失败",null);
    }
}
