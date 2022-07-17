package com.wang.copyeasy.service;

import com.wang.copyeasy.VO.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileConversionService {

    Result UploadPdf(MultipartFile file);

    Result PdfToExcel(String page,String path);
}
