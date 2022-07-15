package com.wang.copyeasy.form;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DBForm {
    @NotNull
    private String url;//连接数据库url
    @NotNull
    private String userName;//用户名
    @NotNull
    private String password;//密码
    @NotNull
    private String tableName;//表名
}
