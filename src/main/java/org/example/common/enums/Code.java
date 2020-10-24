package org.example.common.enums;

import org.example.util.MessageUtil;

public enum Code {
    UNKNOWN_ERROR(-1, "unknown-error"),
    SUCCESS(0, "success"),
    FAIL(500, "fail"),
    ;

    Code(Integer code, String msgKey) {
        this.code = code;
        this.msgKey = msgKey;
    }

    private final Integer code;
    private final String msgKey;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return MessageUtil.message(msgKey);
    }
}
