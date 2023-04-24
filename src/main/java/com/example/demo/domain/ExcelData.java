package com.example.demo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName excel_data
 */
@TableName(value ="excel_data")
@Data
public class ExcelData implements Serializable {

    @TableId(type = IdType.AUTO)
    @ExcelProperty("ID")
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ExcelData other = (ExcelData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getOnSaleDate() == null ? other.getOnSaleDate() == null : this.getOnSaleDate().equals(other.getOnSaleDate()))
            && (this.getFeature() == null ? other.getFeature() == null : this.getFeature().equals(other.getFeature()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getOnSaleDate() == null) ? 0 : getOnSaleDate().hashCode());
        result = prime * result + ((getFeature() == null) ? 0 : getFeature().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", onSaleDate=").append(onSaleDate);
        sb.append(", feature=").append(feature);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
