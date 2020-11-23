package org.example.common.domain.request;

import org.example.common.util.ConvertUtil;
import org.springframework.util.StringUtils;

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

    public String getOrderField() {
        String field = getHumpOrderField();
        return StringUtils.isEmpty(field) ? field : humpToLine(field);
    }

    public String getHumpOrderField() {
        return null == super.get("orderField") ? null : String.valueOf(super.get("orderField"));
    }

    public boolean getIsAsc() {
        return ConvertUtil.getAsBoolean(super.get("isAsc"), true);
    }

    private String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }
}
