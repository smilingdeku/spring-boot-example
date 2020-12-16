package org.example.config.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;

/**
 * Excel 读取监听
 */
public class ExcelReadListener<T> extends AnalysisEventListener<T> {

    private List<T> list;
    private int batchCount;
    private Consumer<List<T>> consumer;

    public ExcelReadListener(int batchCount, Consumer<List<T>> consumer) {
        this.list = Lists.newArrayListWithExpectedSize(batchCount);
        this.batchCount = batchCount;
        this.consumer = consumer;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        list.add(t);
        if (list.size() >= batchCount) {
            consumer.accept(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        consumer.accept(list);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        super.onException(exception, context);
    }
}
