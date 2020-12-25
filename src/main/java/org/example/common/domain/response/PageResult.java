package org.example.common.domain.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.enums.ResultCode;
import org.example.common.util.JsonUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回结果
 **/
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "分页数据")
    private List<T> data;

    @ApiModelProperty(value = "总数")
    private Long total;

    public PageResult(Integer code, String msg, List<T> data, Long total) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total = total;
    }

    public PageResult(ResultCode resultCode, List<T> data, Long total) {
        this(resultCode.getCode(), resultCode.getMsg(), data, total);
    }

    public PageResult(Integer code, String msg, IPage<T> page) {
        this(code, msg, page.getRecords(), page.getTotal());
    }

    public PageResult(ResultCode resultCode, IPage<T> page) {
        this(resultCode.getCode(), resultCode.getMsg(), page);
    }

    public static <T> PageResult<T> build(List<T> data, Long total) {
        return new PageResult<>(ResultCode.SUCCESS, data, total);
    }

    public static <T> PageResult<T> build(IPage<T> page) {
        return new PageResult<>(ResultCode.SUCCESS, page);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
