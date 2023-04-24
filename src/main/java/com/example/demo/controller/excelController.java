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
    public Map<String, String> exportExcel(HttpServletResponse response){
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
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            return map;
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
        // 设置return
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("message", "下载文件成功");
        return map;
    }
}
