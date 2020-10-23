package org.example.common;

import org.example.util.MessageUtil;

public enum Code {
    SUCCESS(0, "SUCCESS"),
    UNKNOWN_ERROR(-1, "UNKNOWN_ERROR");

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
