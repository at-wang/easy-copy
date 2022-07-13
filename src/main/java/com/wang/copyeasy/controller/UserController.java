package com.wang.copyeasy.controller;

import com.wang.copyeasy.VO.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("get")
    public Result get(){
        return Result.builder().flag(true).message("正则").data("dada").build();
    }
}
