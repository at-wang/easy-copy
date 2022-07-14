package com.wang.copyeasy.utils;

import com.wang.copyeasy.form.DBForm;

import java.sql.*;

public class DBUtils {
    public static Connection getConnection(DBForm form) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        String url = "jdbc:oracle:thin:@127.0.0.1:1522:ora11g";
        String user = "wang";
        String password = "wang";

        Class.forName("oracle.jdbc.driver.OracleDriver");//加载数据驱动
        conn = DriverManager.getConnection(form.getUrl(), form.getUserName(), form.getPassword());// 连接数据库


        return conn;
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}