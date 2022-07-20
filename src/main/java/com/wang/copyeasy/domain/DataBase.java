package com.wang.copyeasy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DataBase {
    private String columnName;//字段名
    private String dataType;//数据类型
    private int dataLength;//字段长度
    private String dataComments;//字符串注释
    private String data;//值

    public DataBase(String columnName, String dataType, int dataLength, String dataComments) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.dataLength = dataLength;
        this.dataComments = dataComments;
    }

    public DataBase(String columnName, String dataType, int dataLength) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.dataLength = dataLength;
    }
}