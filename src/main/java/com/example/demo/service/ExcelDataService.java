package com.example.demo.service;

import com.example.demo.domain.ExcelData;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author TMJIE5200
* @description 针对表【excel_data】的数据库操作Service
* @createDate 2023-04-24 11:20:05
*/

@Service
public interface ExcelDataService extends IService<ExcelData> {
    List<ExcelData> selectAllData();
}
