package com.wang.copyeasy.service.impl;


import com.wang.copyeasy.dao.FilePathDao;
import com.wang.copyeasy.service.FileConversionService;
import com.wang.copyeasy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileConversionServiceImpl implements FileConversionService {

    @Value("${file.save-path}")
    private String savePath;

    @Autowired
    private FilePathDao filePathDao;

    @Override
    public Result UploadPdf(MultipartFile file) {
        try {
            if (StringUtils.isEmpty(file)) {
                return new Result(false, "文件不能为空", "");
            }
            //获取要上传文件的名称
            String fileName = file.getOriginalFilename();
            //如果名称为空，返回一个文件名为空的错误
            if (StringUtils.isEmpty(fileName)) {
                return new Result(false, "文件名不能为空", "");
            }
            //获取到后缀名
            String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
            //文件的保存重新按照时间戳命名
            String newName = System.currentTimeMillis() + suffixName;
            File newFile = new File(savePath, newName);
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            try {
                //文件写入
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将这些文件的信息写入到数据库中
//            Files files = new Files(newFile.getPath(), fileName, suffixName);
//            fileMapper.insertFile(files);
//            FilePath filePath=new FilePath(UUID.randomUUID().toString(),newFile.getPath());
//            filePathDao.insert(filePath);
            return new Result(true, "文件上次成功", newFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "文件上传失败", "");
        }

    }

    @Override
    public Result PdfToExcel(String page, String path) {


     /*   String[] argsa = new String[]{"-f=JSON", "-p=" + page, path, "-l"};
        //CommandLineApp.main(argsa);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(CommandLineApp.buildOptions(), argsa);
            StringBuilder stringBuilder = new StringBuilder();
            new CommandLineApp(stringBuilder, cmd).extractTables(cmd);


            //System.out.println("打印返回数据:  " + stringBuilder.toString());
            List<Map<String, Object>> list = JSON.parseObject(stringBuilder.toString(), List.class);
            List<List<Map<String, Object>>> data = (List<List<Map<String, Object>>>) list.get(0).get("data");


            Workbook workbook = new XSSFWorkbook();
            String name = System.currentTimeMillis() + "";

            Sheet sheet = workbook.createSheet(name);
            XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            XSSFFont font = (XSSFFont) workbook.createFont();
            font.setFontName("宋体");
            cellStyle.setFont(font);
            int rowCount = 0;

            for (List<Map<String, Object>> datum : data) {
                Row row = sheet.createRow(rowCount++);
                sheet.setColumnWidth(rowCount, 100 * 20);
                Cell cell = null;
                int cellCount = 0;
                for (Map<String, Object> map : datum) {
                    BigDecimal width = (BigDecimal) map.get("width");
                    int widthInt = width.setScale(1, RoundingMode.HALF_UP).intValue();
                    BigDecimal height = (BigDecimal) map.get("height");
                    short heigntShort = height.setScale(1, RoundingMode.HALF_UP).shortValue();
                    if (widthInt > 0) {
                        sheet.setColumnWidth(cellCount, widthInt * 30);
                        row.setHeight((short) (heigntShort * 20));
                        cell = row.createCell(cellCount++);
                        cell.setCellValue((String) map.get("text"));
                    }
                *//*    PoiResult result = PoiUtils.isMergedRegion(sheet, rowCount, cellCount);
                    if (result.merged) {
                        CellRangeAddress cellAddresses = new CellRangeAddress(result.startRow, result.endRow, result.startCol, result.endCol);
                        sheet.addMergedRegion(cellAddresses);
                    }*//*
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Downloads\\" + name+ ".xlsx");
            workbook.write(fileOutputStream);
            workbook.close();
            return new Result(true,"下载成功","C:\\Users\\Administrator\\Downloads\\" + name+ ".xlsx");*/
//re
            // cellCount=0;
     /*   } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
*/
        return null;
    }
}
