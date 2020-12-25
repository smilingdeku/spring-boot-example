package org.example.common.domain.response;

import io.swagger.annotations.ApiModelProperty;
import org.example.common.enums.ResultCode;
import org.example.common.util.JsonUtil;

import java.io.Serializable;

/**
 * 返回结果
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回码 [0-请求成功] [-1-操作失败]")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultCode resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS, null);
    }

    public static Result<Void> success(String msg) {
        return new Result<>(ResultCode.SUCCESS.getCode(), msg, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> failure() {
        return new Result<>(ResultCode.FAILURE, null);
    }

    public static <T> Result<T> failure(String msg) {
        return new Result<>(ResultCode.FAILURE.getCode(), msg, null);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
