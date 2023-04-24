package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.ExcelData;
import com.example.demo.service.ExcelDataService;
import com.example.demo.mapper.ExcelDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author TMJIE5200
* @description 针对表【excel_data】的数据库操作Service实现
* @createDate 2023-04-24 11:20:05
*/
@Service
public class ExcelDataServiceImpl extends ServiceImpl<ExcelDataMapper, ExcelData>
    implements ExcelDataService{
    @Override
    public List<ExcelData> selectAllData() {
        return baseMapper.selectList(null);
    }
}




