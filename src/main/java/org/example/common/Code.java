package org.example.common;

public enum Code {
    SUCCESS(0, ""),
    UNKNOWN_ERROR(9999, "Unknown error");

    private final Integer code;
    private final String msg;

    Code(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
