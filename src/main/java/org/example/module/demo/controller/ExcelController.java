package org.example.module.demo.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.api.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.example.common.domain.entity.ExcelCellComment;
import org.example.common.util.ExcelUtil;
import org.example.module.demo.domain.dto.ExcelTemplateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/demo/excel")
public class ExcelController {

    private static Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Value("${oss.qiniu.access-key}")
    private String value;

    @RequestMapping("/value")
    public R<String> get(){
        return R.ok(value);
    }

    /**
     * 下载excel文件
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        ExcelWriter excelWriter = null;
        try {
            // 设置头部
            ExcelUtil.setResponseHeader(response, "会员数据", ExcelTypeEnum.XLSX);

            // 创建excelWriter和writeShell
            ServletOutputStream outputStream = response.getOutputStream();

            excelWriter = ExcelUtil.createExcelWriter(outputStream, ExcelTemplateDTO.class);
            WriteSheet writeSheet = createWriteSheet();

            for (int pageNumber = 0; pageNumber < 5; pageNumber++) {
                List<ExcelTemplateDTO> infos = mock();
                ExcelUtil.writeExcel(excelWriter, writeSheet, infos);
            }
        } catch (Exception e) {
            logger.error("Function[download]", e);
        } finally {
            ExcelUtil.closeExcelWriter(excelWriter);
        }
    }

    private WriteSheet createWriteSheet() {
        Map<Integer, String[]> selectorMap = new HashMap<>(8);

        String[] genderArr = { "男", "女", "未知" };
        String[] idTypeArr = { "身份证", "护照" };

        selectorMap.put(1, idTypeArr);
        selectorMap.put(5, genderArr);


        List<ExcelCellComment> commentList = new ArrayList<>();
        for (int index = 0; index < 6; index++) {
            ExcelCellComment cellComment = new ExcelCellComment();
            cellComment.setRow(0);
            cellComment.setCol(index);
            cellComment.setContent("备注" + index);

            commentList.add(cellComment);
        }


        return ExcelUtil.createWriteSheet("成员数据", commentList, selectorMap);
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
