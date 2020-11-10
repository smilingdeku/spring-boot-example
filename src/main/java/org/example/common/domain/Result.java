package org.example.common.domain;


import org.example.common.enums.Code;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Code code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
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

    public static Result<Void> success() {
        return new Result<>(Code.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(Code.SUCCESS, data);
    }

    public static Result<Void> failure() {
        return new Result<>(Code.FAILURE, null);
    }

    public static Result<Void> failure(String msg) {
        return new Result<>(Code.FAILURE.getCode(), msg);
    }
}
