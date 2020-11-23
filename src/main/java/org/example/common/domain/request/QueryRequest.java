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

    public String getKeyword() {
        return ConvertUtil.toStr(super.get("keyword"), null);
    }

    public Integer getPageIndex() {
        return ConvertUtil.toInteger(super.get("pageIndex"), 1);
    }

    public Integer getPageSize() {
        return ConvertUtil.toInteger(super.get("pageSize"), 10);
    }

    public String getLineOrderField() {
        String field = getOrderField();
        return StringUtils.isEmpty(field) ? field : humpToLine(field);
    }

    public String getOrderField() {
        return ConvertUtil.toStr(super.get("orderField"), null);
    }

    public boolean getIsAsc() {
        return ConvertUtil.toBoolean(super.get("isAsc"), true);
    }

    private String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }
}
