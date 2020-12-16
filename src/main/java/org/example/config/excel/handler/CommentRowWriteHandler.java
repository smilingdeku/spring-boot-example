package org.example.config.excel.handler;

import com.alibaba.excel.write.handler.AbstractRowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.example.common.domain.entity.ExcelCellComment;

import java.util.List;

/**
 * Excel 添加批注
 *
 * @author walle
 * @version V1.0
 * @since 2020-12-12 23:09
 */
public class CommentRowWriteHandler extends AbstractRowWriteHandler {

    private List<ExcelCellComment> commentList;

    public CommentRowWriteHandler(List<ExcelCellComment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                Row row, Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            Sheet sheet = writeSheetHolder.getSheet();
            Drawing<?> drawingPatriarch = sheet.createDrawingPatriarch();
            commentList.forEach(item -> {
                XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0,
                        (short) item.getCol(), 0,
                        (short) item.getCol() + 1, 0);
                Comment comment = drawingPatriarch.createCellComment(anchor);
                // 设置批注信息
                comment.setString(new XSSFRichTextString(item.getContent()));
                // 将批注添加到单元格
                sheet.getRow(item.getRow()).getCell(item.getCol()).setCellComment(comment);
            });
        }
    }
}
