package org.example.common.domain.request;

import org.example.common.util.ConvertUtil;

import java.util.LinkedHashMap;

/**
 * @author cark
 * @since 2020-11-15
 **/
public class QueryRequest extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Integer getPageIndex() {
        return ConvertUtil.getAsInteger(super.get("pageIndex"), 1);
    }

    public Integer getPageSize() {
        return ConvertUtil.getAsInteger(super.get("pageSize"), 10);
    }
}
