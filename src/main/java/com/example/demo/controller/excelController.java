package com.example.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.example.demo.domain.ExcelData;
import com.example.demo.domain.ExcelTitle;
import com.example.demo.service.ExcelDataService;
import com.example.demo.service.ExcelTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exportExcel")
public class excelController {
    @Autowired
    ExcelDataService excelDataService;

    @Autowired
    ExcelTitleService excelTitleService;
    @GetMapping("/download")
    public void exportExcel(HttpServletResponse response){
        // 获取表头
        List<ExcelTitle> getAllExcelHead = excelTitleService.selectExcelHead();
        List<String> excelHeadList = getAllExcelHead.stream().map(ExcelTitle::getDisplaylabel).collect(Collectors.toList());
        List<List<String>> excelHead = new ArrayList<>();
        excelHead.add(Collections.singletonList("序号"));
        excelHeadList.forEach(h -> excelHead.add(Collections.singletonList(h)));
        // 获取数据
        List<ExcelData> excelData = excelDataService.selectAllData();
        // 设置导出响应
        String fileName = null;
        try {
            fileName = URLEncoder.encode("测试.xlsx", "UTF-8").replaceAll("\\+", "%20");
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-Disposition", "attachment;filename=" + fileName);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        // 写入excel
        OutputStream outputStream = null;
        ExcelWriter excelWriter = null;
        try {
            outputStream = response.getOutputStream();
            excelWriter = EasyExcel.write(response.getOutputStream()).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();
            writeSheet.setHead(excelHead);
            excelWriter.write(excelData, writeSheet);
        } catch (IOException e) {
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
        }finally {
            // 处理流
            if (excelWriter != null) {
                excelWriter.finish();
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/staticDownload")
    public void exportStaticExcel(HttpServletResponse response) throws IOException {
        // 1.设置表头

        // 2.处理数据
        List<ExcelData> excelData = excelDataService.selectAllData();
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelData.class).sheet().doWrite(excelData);

    }
}
