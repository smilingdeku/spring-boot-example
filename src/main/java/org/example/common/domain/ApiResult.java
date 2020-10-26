package org.example.common.domain;


import org.example.common.enums.Code;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private T data;

    public ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(Code code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }

    public ApiResult(Code code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    @SuppressWarnings({"rawtypes"})
    public static ApiResult success(Object data) {
        return new ApiResult<>(Code.SUCCESS, data);
    }

    @SuppressWarnings({"rawtypes"})
    public static ApiResult fail(Object data) {
        return new ApiResult<>(Code.FAIL, data);
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
}
