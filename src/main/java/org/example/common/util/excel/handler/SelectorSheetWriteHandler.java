package org.example.common.util.excel.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

import java.util.Map;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

/**
 * excel下面选择Handler
 *
 * @author walle
 * @version V1.0
 * @since 2020-12-12 22:55
 */
public class SelectorSheetWriteHandler implements SheetWriteHandler {

    private Map<Integer, String[]> columnSelectorMap;

    /**
     * 下拉选择框
     * <p>
     * key: 对应excelTemplate的@ExcelProperty注解的index值
     * <p>
     * value: 该下拉选择框的下拉内容
     *
     * @param columnSelectorMap 下拉选择框数据
     */
    public SelectorSheetWriteHandler(Map<Integer, String[]> columnSelectorMap) {
        this.columnSelectorMap = columnSelectorMap;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();

        //开始设置下拉框
        DataValidationHelper helper = sheet.getDataValidationHelper();
        for (Map.Entry<Integer, String[]> entry : columnSelectorMap.entrySet()) {
            /* 起始行、终止行、起始列、终止列 */
            int maxExcelRow = 1048575;
            CellRangeAddressList addressList = new CellRangeAddressList(1, maxExcelRow, entry.getKey(), entry.getKey());
            /* 设置下拉框数据 */
            DataValidationConstraint constraint = helper.createExplicitListConstraint(entry.getValue());
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            /* 处理Excel兼容性问题 */
            if (dataValidation instanceof XSSFDataValidation) {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            } else {
                dataValidation.setSuppressDropDownArrow(false);
            }
            sheet.addValidationData(dataValidation);
        }

    }
}
