package com.wang.copyeasy.UserTest;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.wang.copyeasy.utils.PoiUtils;
import com.wang.copyeasy.vo.PoiResult;
import io.github.jonathanlink.PDFLayoutTextStripper;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import technology.tabula.CommandLineApp;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class test1 {


    @Resource
    private DruidDataSource druidDataSource;

    @Test
    void test() {
        String string = null;
        try {
            PDFParser pdfParser = new PDFParser(new RandomAccessFile(new File("C:\\Users\\Administrator\\Downloads\\test.pdf"), "rw"));
            pdfParser.parse();
            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            pdfTextStripper.setSortByPosition(true);
            pdfTextStripper.setStartPage(0);
            pdfTextStripper.setEndPage(1);

            string = pdfTextStripper.getText(pdDocument);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String x = string.replaceAll(" ", "");
        System.out.println(x);
    }


    @Test
    void test2() {
        String result = "";
        File file = new File("C:\\Users\\Administrator\\Downloads\\test.pdf");
        FileInputStream in = null;
        try {
            in = new FileInputStream("C:\\Users\\Administrator\\Downloads");
            // 新建一个PDF解析器对象
            PDFParser parser = new PDFParser(new RandomAccessFile(file, "rw"));
            // 对PDF文件进行解析
            parser.parse();
            // 获取解析后得到的PDF文档对象
            PDDocument pdfdocument = parser.getPDDocument();
            int size = pdfdocument.getNumberOfPages();
            // 新建一个PDF文本剥离器
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(false); //sort:设置为true则按照行进行读取，默认是false
            //一页一页读取
            for (int i = 0; i <= 1; i++) {
                // 设置起始页
                stripper.setStartPage(i);
                // 设置结束页
                stripper.setEndPage(i);
                // 从PDF文档对象中剥离文本
                String pageStr = stripper.getText(pdfdocument);
                result = result + pageStr + "\n" + "PDF解析第" + i + "页\n";
            }

            //一次读取完
            // 设置起始页
            // stripper.setStartPage(from);
            // 设置结束页
            //  stripper.setEndPage(end);
            // 从PDF文档对象中剥离文本
            //  String result = stripper.getText(pdfdocument);

        } catch (Exception e) {
            System.out.println("读取PDF文件" + file.getAbsolutePath() + "生失败！" + e);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
        System.out.println(result);
    }

    @Test
    void test3() {
        Document document = null;
        PdfCopy copy = null;
        int end = 1;
        String respdfFile = "C:\\Users\\Administrator\\Downloads\\test.pdf";
        try {
            PdfReader reader = new PdfReader(respdfFile);
            int n = reader.getNumberOfPages();
            if (end == 0) {
                end = n;
            }
            //上传pdf 地址https://www.cleverpdf.com/pdf/uploadFiles
            // files:
            // files: (二进制)
            //excel转换https://www.cleverpdf.com/pdf/doProcess.do
            //url: uploadFiles/file/1637450036/test.pdf
            //index: 1637450036
            //pid: 1
            //oid: 3
            //status: 0
            //pwd:
            //formatv1: 2
            //formatv2: 2
            //下载https://www.cleverpdf.com/pdf/fileDownload 表单数据 index: 2664197
            String staticpath = respdfFile.substring(0, respdfFile.lastIndexOf("\\") + 1);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream("C:\\Users\\Administrator\\Downloads\\3.pdf"));
            document.open();
            for (int j = 11; j <= 11; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test4() {

    }


    @Test
    public void test5() throws ParseException, IOException {
        //-f导出格式,默认CSV  (一定要大写)
        //-p 指导出哪页,all是所有
        //path　D:\\1xx.pdf
        //-l 强制使用点阵模式提取PDF　（关键在于这儿）
        String[] argsa = new String[]{"-f=JSON", "-p=4", "C:\\Users\\Administrator\\Downloads\\test2.pdf", "-l"};
        //CommandLineApp.main(argsa);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(CommandLineApp.buildOptions(), argsa);
        StringBuilder stringBuilder = new StringBuilder();
        new CommandLineApp(stringBuilder, cmd).extractTables(cmd);


        //System.out.println("打印返回数据:  " + stringBuilder.toString());
        List<Map<String, Object>> list = JSON.parseObject(stringBuilder.toString(), List.class);
        System.out.println(list);
        List<List<Map<String, Object>>> data = (List<List<Map<String, Object>>>) list.get(0).get("data");
        System.out.println(data);
//https://blog.csdn.net/weixin_31188927/article/details/115963262?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165818895516780357281315%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=165818895516780357281315&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-1-115963262-null-null.142^v32^new_blog_pos_by_title,185^v2^control&utm_term=poi%E5%AF%BC%E5%87%BAexcel%E6%8D%A2%E8%A1%8C%E8%87%AA%E9%80%82%E5%BA%94&spm=1018.2226.3001.4187

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(System.currentTimeMillis() + "");
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontName("宋体");
        cellStyle.setFont(font);
        int rowCount = 0;

        for (List<Map<String, Object>> datum : data) {
            Row row = sheet.createRow(rowCount++);
            //sheet.setColumnWidth(rowCount, 100 * 20);
            Cell cell = null;
            int cellCount = 0;
            for (Map<String, Object> map : datum) {
                BigDecimal width = (BigDecimal) map.get("width");
                int widthInt = width.setScale(1, RoundingMode.HALF_UP).intValue();
                BigDecimal height = (BigDecimal) map.get("height");
                short heigntShort = height.setScale(1, RoundingMode.HALF_UP).shortValue();
                if (widthInt > 0) {
                    sheet.setColumnWidth(cellCount, widthInt * 80);
                    sheet.autoSizeColumn(rowCount);
                    row.setHeight((short) (heigntShort * 40));
                    cell = row.createCell(cellCount++);
                    String text = (String) map.get("text");
                    String s = text.replaceAll("\r", "\r\n");
                    cell.setCellValue(new XSSFRichTextString(s));
                }
                PoiResult result = PoiUtils.isMergedRegion(sheet, rowCount, cellCount);
                if (result.merged) {
                    CellRangeAddress cellAddresses = new CellRangeAddress(result.startRow, result.endRow, result.startCol, result.endCol);
                    sheet.addMergedRegion(cellAddresses);
                }
            }
            // cellCount=0;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Downloads\\" + System.currentTimeMillis() + ".xlsx");
            workbook.write(fileOutputStream);
            workbook.close();
            System.out.println("成功");
        } catch (IOException e) {
            System.out.println("失败");

        }
    }

    @Test
    void test6() throws ParseException {
        String[] args = new String[]{"-f=JSON", "-p=3", "C:\\Users\\Administrator\\Downloads\\test2.pdf", "-l"};
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(CommandLineApp.buildOptions(), args);
        StringBuilder stringBuilder = new StringBuilder();
        new CommandLineApp(stringBuilder, cmd).extractTables(cmd);
        System.out.println(stringBuilder);
        JSONArray objects = JSON.parseArray(stringBuilder.toString());
        for (Object object : objects) {
            System.out.println(object);
        }


    }

}
