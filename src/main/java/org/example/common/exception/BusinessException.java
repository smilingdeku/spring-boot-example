package org.example.common.exception;


import org.example.common.enums.ResultCode;

public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        this(ResultCode.FAILURE.getCode(), message);
    }

    public BusinessException(ResultCode code) {
        super(code.getMsg());
        this.code = code.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
