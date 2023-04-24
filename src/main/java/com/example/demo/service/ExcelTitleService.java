package com.example.demo.service;

import com.example.demo.domain.ExcelTitle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TMJIE5200
* @description 针对表【excel_title】的数据库操作Service
* @createDate 2023-04-24 11:26:56
*/
public interface ExcelTitleService extends IService<ExcelTitle> {
    List<ExcelTitle> selectExcelHead();
}
