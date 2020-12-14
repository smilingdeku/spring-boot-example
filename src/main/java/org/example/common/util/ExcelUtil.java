package org.example.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.example.common.domain.entity.ExcelCellComment;
import org.example.config.excel.handler.CommentRowWriteHandler;
import org.example.config.excel.handler.SelectorSheetWriteHandler;
import org.example.config.excel.listener.ExcelReadListener;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * excel工具类
 *
 * <pre>
 * easyexcel官方文档: https://www.yuque.com/easyexcel/doc/write
 * </pre>
 *
 * @author walle
 * @version V1.0
 * @since 2020-12-12 22:04
 */
public class ExcelUtil {

    /**
     * 设置头部信息
     *
     * @param response HttpServletResponse
     * @param fileName 文件名称
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName, ExcelTypeEnum excelType) throws Exception {
        String encodeName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + encodeName + excelType.getValue());
    }

    /**
     * 读取 Excel 进行批量操作
     *
     * @param file       上传文件
     * @param head       头部信息
     * @param sheetNo    工作表序号
     * @param sheetName  工作表名称
     * @param batchCount 批量处理数量
     * @param consumer   批量操作
     */
    public static <T> void readExcel(MultipartFile file, Class<T> head, Integer sheetNo, String sheetName, int batchCount, Consumer<List<T>> consumer) throws IOException {
        ExcelReadListener<T> readListener = new ExcelReadListener<>(batchCount, consumer);
        EasyExcel.read(file.getInputStream(), head, readListener).sheet(sheetNo, sheetName).doRead();;
    }

    /**
     * 读取 Excel 进行批量操作
     *
     * @param file       上传文件
     * @param head       头部信息
     * @param sheetNo    工作表序号
     * @param batchCount 批量处理数量
     * @param consumer   批量操作
     */
    public static <T> void readExcel(MultipartFile file, Class<T> head, Integer sheetNo, int batchCount, Consumer<List<T>> consumer) throws IOException {
        readExcel(file, head, sheetNo, null, batchCount, consumer);
    }

    /**
     * 读取 Excel 进行批量操作
     *
     * @param file       上传文件
     * @param head       头部信息
     * @param sheetName  工作表名称
     * @param batchCount 批量处理数量
     * @param consumer   批量操作
     */
    public static <T> void readExcel(MultipartFile file, Class<T> head, String sheetName, int batchCount, Consumer<List<T>> consumer) throws IOException {
        readExcel(file, head, null, sheetName, batchCount, consumer);
    }

    /**
     * 读取 Excel 进行批量操作
     *
     * @param file       上传文件
     * @param head       头部信息
     * @param batchCount 批量处理数量
     * @param consumer   批量操作
     */
    public static <T> void readExcel(MultipartFile file, Class<T> head, int batchCount, Consumer<List<T>> consumer) throws IOException {
        readExcel(file, head, null, null, batchCount, consumer);
    }

    /**
     * 读取 Excel 进行批量操作
     *
     * @param pathName   文件路径
     * @param head       头部信息
     * @param sheetNo    工作表序号
     * @param sheetName  工作表名称
     * @param batchCount 批量处理数量
     * @param consumer   批量操作
     */
    public static <T> void readExcel(String pathName, Class<T> head, Integer sheetNo, String sheetName, int batchCount, Consumer<List<T>> consumer) {
        ExcelReadListener<T> readListener = new ExcelReadListener<>(batchCount, consumer);
        EasyExcel.read(pathName, head, readListener).sheet(sheetNo, sheetName).doRead();;
    }

    /**
     * 读取 Excel 进行批量操作
     *
     * @param pathName   文件路径
     * @param head       头部信息
     * @param sheetNo    工作表序号
     * @param batchCount 批量处理数量
     * @param consumer   批量操作
     */
    public static <T> void readExcel(String pathName, Class<T> head, Integer sheetNo, int batchCount, Consumer<List<T>> consumer) {
        readExcel(pathName, head, sheetNo, null, batchCount, consumer);
    }

    /**
     * 读取 Excel 进行批量操作
     *
     * @param pathName   文件路径
     * @param head       头部信息
     * @param sheetName  工作表名称
     * @param batchCount 批量处理数量
     * @param consumer   批量操作
     */
    public static <T> void readExcel(String pathName, Class<T> head, String sheetName, int batchCount, Consumer<List<T>> consumer) {
        readExcel(pathName, head, null, sheetName, batchCount, consumer);
    }

    /**
     * 读取 Excel 进行批量操作
     *
     * @param pathName   文件路径
     * @param head       头部信息
     * @param batchCount 批量处理数量
     * @param consumer   批量操作
     */
    public static <T> void readExcel(String pathName, Class<T> head, int batchCount, Consumer<List<T>> consumer) {
        readExcel(pathName, head, null, null, batchCount, consumer);
    }


    /**
     * 创建 ExcelWriter
     *
     * @param os   输出流
     * @param head 头部信息
     * @return ExcelWriter
     */
    public static ExcelWriter createExcelWriter(OutputStream os, Class<?> head) {
        ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();

        excelWriterBuilder.autoCloseStream(true);
        excelWriterBuilder.file(os);
        excelWriterBuilder.head(head);

        return excelWriterBuilder.build();
    }

    /**
     * 创建 WriteSheet
     *
     * @param sheetName 工作表名
     * @param handlers  写入处理
     * @return WriteSheet
     */
    public static WriteSheet createWriteSheet(String sheetName, WriteHandler ...handlers) {
        ExcelWriterSheetBuilder writerSheetBuilder = EasyExcel.writerSheet(sheetName);
        for (WriteHandler handler : handlers) {
            if (Objects.nonNull(handler)) {
                writerSheetBuilder.registerWriteHandler(handler);
            }
        }
        return writerSheetBuilder.build();
    }

    /**
     * 创建 WriteSheet
     *
     * @param sheetName   工作表名
     * @param commentList 批注列表
     * @param selectorMap 下拉框 Map
     * @return WriteSheet
     */
    public static WriteSheet createWriteSheet(String sheetName, List<ExcelCellComment> commentList, Map<Integer, String[]> selectorMap) {
        CommentRowWriteHandler commentRowWriteHandler = null;
        if (!CollectionUtils.isEmpty(commentList)) {
            commentRowWriteHandler = new CommentRowWriteHandler(commentList);
        }
        SelectorSheetWriteHandler selectorSheetWriteHandler = null;
        if (!CollectionUtils.isEmpty(selectorMap)) {
            selectorSheetWriteHandler = new SelectorSheetWriteHandler(1, selectorMap);
        }
        return createWriteSheet(sheetName, commentRowWriteHandler, selectorSheetWriteHandler);
    }


}
