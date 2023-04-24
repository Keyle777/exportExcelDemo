package com.example.demo.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class staticExcelHead {

    @ExcelProperty(value = "序号", index = 0)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    private String id;

    @ExcelProperty("商品名称")
    @ColumnWidth(20)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    private String name;

    @ExcelProperty("价格")
    @ColumnWidth(20)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    private BigDecimal price;

    @ColumnWidth(50)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("生产日期")
    private Date onSaleDate;

    @ExcelProperty("生产商")
    @ColumnWidth(20)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    private String feature;
}
