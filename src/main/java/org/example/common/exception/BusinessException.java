package org.example.common.exception;


import org.example.common.enums.Code;

public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        this(Code.FAILURE.getCode(), message);
    }

    public BusinessException(Code code) {
        super(code.getMsg());
        this.code = code.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
