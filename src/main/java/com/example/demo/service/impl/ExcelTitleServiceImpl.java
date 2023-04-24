package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.ExcelTitle;
import com.example.demo.service.ExcelTitleService;
import com.example.demo.mapper.ExcelTitleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author TMJIE5200
* @description 针对表【excel_title】的数据库操作Service实现
* @createDate 2023-04-24 11:26:56
*/
@Service
public class ExcelTitleServiceImpl extends ServiceImpl<ExcelTitleMapper, ExcelTitle>
    implements ExcelTitleService{
    @Override
    public List<ExcelTitle> selectExcelHead() {
        return baseMapper.selectList(null);
    }
}




