package org.example.common.util.excel.handler;

import com.alibaba.excel.write.handler.AbstractRowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;

import java.util.List;

import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

/**
 * @author walle
 * @version V1.0
 * @since 2020-12-12 23:09
 */
public class CommentWriteHandler extends AbstractRowWriteHandler {

    public static class Remark {
        /**
         * 行号,从0开始
         */
        private int rowNum;
        /**
         * 单元格,从0开始
         */
        private int cellNum;
        /**
         * 备注
         */
        private String comment;

        public int getRowNum() {
            return rowNum;
        }

        public void setRowNum(int rowNum) {
            this.rowNum = rowNum;
        }

        public int getCellNum() {
            return cellNum;
        }

        public void setCellNum(int cellNum) {
            this.cellNum = cellNum;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    private List<Remark> remarkList;

    public CommentWriteHandler(List<Remark> remarkList) {
        this.remarkList = remarkList;
    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
        Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            Sheet sheet = writeSheetHolder.getSheet();
            Drawing<?> drawingPatriarch = sheet.createDrawingPatriarch();

            /*
             * Creates a new client anchor and sets the top-left and bottom-right
             * coordinates of the anchor by cell references and offsets.
             * Sets the type to {@link org.apache.poi.ss.usermodel.ClientAnchor.AnchorType#MOVE_AND_RESIZE}.
             *
             * @param dx1  the x coordinate within the first cell.
             * @param dy1  the y coordinate within the first cell.
             * @param dx2  the x coordinate within the second cell.
             * @param dy2  the y coordinate within the second cell.
             * @param col1 the column (0 based) of the first cell.
             * @param row1 the row (0 based) of the first cell.
             * @param col2 the column (0 based) of the second cell.
             * @param row2 the row (0 based) of the second cell.
             */

            for (Remark remark : remarkList) {
                XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) remark.getCellNum(), 0,
                    (short) remark.getCellNum() + 1, 0);
                Comment comment = drawingPatriarch.createCellComment(anchor);
                // 输入批注信息
                comment.setString(new XSSFRichTextString(remark.getComment()));

                // 将批注添加到单元格对象中
                sheet.getRow(remark.getRowNum()).getCell(remark.getCellNum()).setCellComment(comment);
            }
        }
    }
}
