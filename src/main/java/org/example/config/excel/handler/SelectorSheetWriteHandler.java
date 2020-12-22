package org.example.config.excel.handler;

import com.alibaba.excel.write.handler.AbstractSheetWriteHandler;
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
 * Excel 添加下拉框
 *
 * @author walle&eva
 * @version V1.0
 * @since 2020-12-12 22:55
 */
public class SelectorSheetWriteHandler extends AbstractSheetWriteHandler {

    private static final int MAX_EXCEL_ROW = 1048575;

    private int firstRow;
    private Map<Integer, String[]> selectorMap;

    public SelectorSheetWriteHandler(int firstRow, Map<Integer, String[]> selectorMap) {
        this.firstRow = firstRow;
        this.selectorMap = selectorMap;
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();

        DataValidationHelper helper = sheet.getDataValidationHelper();

        selectorMap.forEach((key, value) -> {
            CellRangeAddressList addressList = new CellRangeAddressList(firstRow, MAX_EXCEL_ROW, key, key);
            // 设置下拉框数据
            DataValidationConstraint constraint = helper.createExplicitListConstraint(value);
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            // 处理 Excel 兼容性问题
            if (dataValidation instanceof XSSFDataValidation) {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            } else {
                dataValidation.setSuppressDropDownArrow(false);
            }
            sheet.addValidationData(dataValidation);
        });
    }
}
