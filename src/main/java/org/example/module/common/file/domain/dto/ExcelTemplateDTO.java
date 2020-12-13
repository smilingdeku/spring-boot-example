package org.example.module.common.file.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;

import org.example.common.util.JsonUtil;

/**
 * @author walle
 * @version V1.0
 * @since 2020-12-12 22:48
 */
public class ExcelTemplateDTO {

    @ExcelProperty(value = { "序号" }, index = 0)
    private String seriesNumber;

    @ExcelProperty(value = { "证件类型" }, index = 1)
    private String idType;

    @ExcelProperty(value = { "证件号码" }, index = 2)
    private String idNumber;

    @ExcelProperty(value = { "名称" }, index = 3)
    private String name;

    @ExcelProperty(value = { "出生日期" }, index = 4)
    private String birthday;

    @ExcelProperty(value = { "性别" }, index = 5)
    private String gender;

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
