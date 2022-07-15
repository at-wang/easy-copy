package com.wang.copyeasy.controller;

import com.wang.copyeasy.VO.Result;
import com.wang.copyeasy.form.DBForm;
import com.wang.copyeasy.service.CollectDBService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CollectDBController {

    @Autowired
    private CollectDBService collectDBService;


    @PostMapping("/checkCollect")
    public Result checkCollect(@RequestBody  DBForm form)throws Exception{
        if (Strings.isBlank(form.getPassword())){
            return new Result(false,"请检查格式","");
        }
        if (Strings.isBlank(form.getUrl())){
            return new Result(false,"请检查格式","");
        }
        if (Strings.isBlank(form.getUserName())){
            return new Result(false,"请检查格式","");
        }
        return collectDBService.checkCollect(form);
    }
    @PostMapping("/batchInsertSql")
    public Result batchInsertSql(@RequestBody DBForm form) {
        List<StringBuilder> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StringBuilder stringBuilder = collectDBService.batchInsertSql(form);
            int lastIndexOf = stringBuilder.lastIndexOf(",");
            String substring = stringBuilder.substring(0, lastIndexOf);
            list.add(new StringBuilder().append(substring).append(");"));
        }
        return new Result(true,"成功",list);
    }
}
