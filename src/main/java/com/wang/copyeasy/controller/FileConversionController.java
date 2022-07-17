package com.wang.copyeasy.controller;


import com.wang.copyeasy.VO.Result;
import com.wang.copyeasy.service.FileConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class FileConversionController {

    @Autowired
    private FileConversionService fileConversionService;


    @PostMapping("/UploadPdf")
    public Result UploadPdf(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Result(false, "文件不能为空", "");
        }
        return fileConversionService.UploadPdf(file);
    }


    @GetMapping("/PdfToExcel")
    public Result PdfToExcel( @RequestParam String page, @RequestParam String path)  {
        return  fileConversionService.PdfToExcel(page,path);
    }

    @GetMapping("/test")
    public String test(String test){
        return test;
    }


}
