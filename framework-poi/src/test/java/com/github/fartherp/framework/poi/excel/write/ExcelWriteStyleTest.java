/*
 * Copyright (c) 2017. CK. All rights reserved.
 */

package com.github.fartherp.framework.poi.excel.write;

import com.github.fartherp.framework.poi.excel.ExcelBuilder;
import com.github.fartherp.framework.poi.excel.ExcelDto;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: CK
 * @date: 2017/11/25
 */
public class ExcelWriteStyleTest {
    @SuppressWarnings("all")
    @Test
    public void writeExcel() throws Exception {
        String[] title = new String [6];
        title[0] = "登录时间";
        title[1] = "用户名";
        title[2] = "访问端";
        title[3] = "版本系统";
        title[4] = "登录IP";
        title[5] = "状态";
        String head = "用户登录日志";
        String fileName = "D:\\style.xlsx";
        String condition = "用户类型：投资用户    登录时间：XXXX-XX-XX至XXXX-XX-XX     查询条件：XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

        ExcelWrite<ExcelDto> excelDtoExcelWrite = ExcelBuilder.buildFileWrite(title, fileName, getList());
        ExcelWriteStyle<ExcelDto> writeStyle = new ExcelWriteStyle(excelDtoExcelWrite);
        writeStyle.condition(condition).head(head).writeExcel(
                new ExcelWriteStyle.DefaultWriteDeal<ExcelDto>() {
                    public String[] dealBean(ExcelDto obj) {
                        String[] result = new String[6];
                        result[0] = obj.getTime();
                        result[1] = obj.getName();
                        result[2] = obj.getClient();
                        result[3] = obj.getVersion();
                        result[4] = obj.getIp();
                        result[5] = obj.getStatus() + "";
                        return result;
                    }
                });
    }

    public static List<ExcelDto> getList() {
        List<ExcelDto> excelDtos = new ArrayList<ExcelDto>();
        for (int i = 1; i < 4; i++) {
            ExcelDto dto = new ExcelDto();
            dto.setTime("2017-10-20 10:10:1" + i);
            dto.setName("name" + i);
            dto.setClient("client"  + i);
            dto.setVersion("1.0." + i);
            dto.setIp("192.168.1." + i);
            dto.setStatus(i);
            excelDtos.add(dto);
        }
        return excelDtos;
    }

}