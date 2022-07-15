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

}