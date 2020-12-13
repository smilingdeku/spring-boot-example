package org.example.common.util.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @author walle
 * @version V1.0
 * @since 2020-12-13 00:13
 */
public class ExcelAnalysisEventListener<T> extends AnalysisEventListener<T> {

    @Override
    public void invoke(T value, AnalysisContext context) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
