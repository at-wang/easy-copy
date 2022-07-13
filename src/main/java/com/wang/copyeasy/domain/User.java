package com.wang.copyeasy.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@TableName("USER2")
public class User {
    private int id;
    private int age;
    private String name;
}
