package com.wang.copyeasy.controller;

import com.wang.copyeasy.domain.User;
import com.wang.copyeasy.form.DBForm;
import com.wang.copyeasy.service.EasyRequestService;
import com.wang.copyeasy.service.UserService;
import com.wang.copyeasy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EasyRequestController {

    @Autowired
    private EasyRequestService easyRequestService;

    @Autowired
    private UserService userService;

    @PostMapping("/getFormParams")
    public Result getFormParams(@RequestBody DBForm dbForm){
        return easyRequestService.queryFormParams(dbForm);
    }


    @PostMapping("/user")
    public String addUser(@RequestBody User user){
        System.out.println(user);
        return userService.insertUser(user);
    }

    @PostMapping("/user2")
    public String addUser2(@RequestBody User user){
        user.setNAME("王英琪");
        return userService.insertUser(user);
    }
}
