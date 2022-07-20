package com.wang.copyeasy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("USER2")
public class User {


    @TableField("NAME")
    private String NAME;
    @TableField("AGE")
    private int AGE;

    @TableField("TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date TIME;


}
