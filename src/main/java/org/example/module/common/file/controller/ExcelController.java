package org.example.module.common.file.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.example.common.util.excel.ExcelUtil;
import org.example.common.util.excel.handler.CommentWriteHandler;
import org.example.common.util.excel.handler.SelectorSheetWriteHandler;
import org.example.module.common.file.domain.dto.ExcelTemplateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文档地址:
 * <p>
 * https://www.yuque.com/easyexcel/doc/write
 *
 * @author walle
 * @version V1.0
 * @since 2020-12-07 09:53
 */

@RestController
@RequestMapping("/common/excel")
public class ExcelController {

    private static Logger logger = LoggerFactory.getLogger(ExcelController.class);

    /**
     * 下载excel文件
     */
    @PostMapping("/download")
    public void download(HttpServletResponse response) {
        try {

            // 设置头部
            ExcelUtil.setResponseHeader(response, "会员数据");

            // 创建excelWriter和writeShell
            ServletOutputStream outputStream = response.getOutputStream();
            ExcelWriter excelWriter = ExcelUtil.createExcelWriter(outputStream, ExcelTemplateDTO.class);
            WriteSheet writeSheet = createWriteSheet();

            for (int pageNumber = 0; pageNumber < 5; pageNumber++) {
                List<ExcelTemplateDTO> infos = mock();
                excelWriter.write(infos, writeSheet);
            }

            excelWriter.finish();
        } catch (Exception e) {
            logger.error("Function[download]", e);
        }
    }

    private WriteSheet createWriteSheet() {
        Map<Integer, String[]> selectorMap = new HashMap<>(8);

        String[] genderArr = { "男", "女", "未知" };
        String[] idTypeArr = { "身份证", "护照" };

        selectorMap.put(1, idTypeArr);
        selectorMap.put(5, genderArr);
        SelectorSheetWriteHandler selectorSheetWriteHandler = new SelectorSheetWriteHandler(selectorMap);

        List<CommentWriteHandler.Remark> remarkList = new ArrayList<>();
        for (int index = 0; index < 6; index++) {
            CommentWriteHandler.Remark remark = new CommentWriteHandler.Remark();
            remark.setRowNum(0);
            remark.setCellNum(index);
            remark.setComment("备注" + index);

            remarkList.add(remark);
        }
        CommentWriteHandler commentWriteHandler = new CommentWriteHandler(remarkList);

        return ExcelUtil
            .createWriteSheet("成员数据", selectorSheetWriteHandler, commentWriteHandler, Collections.emptySet());
    }

    private List<ExcelTemplateDTO> mock() {
        List<ExcelTemplateDTO> list = new ArrayList<>();

        for (int index = 0; index < 3; index++) {
            ExcelTemplateDTO p = new ExcelTemplateDTO();
            p.setBirthday("20200306");
            p.setName("测试");
            p.setGender("未知");
            p.setIdType("身份证");
            p.setSeriesNumber(System.currentTimeMillis() + "");
            p.setIdNumber("123");

            list.add(p);
        }
        return list;
    }
}
