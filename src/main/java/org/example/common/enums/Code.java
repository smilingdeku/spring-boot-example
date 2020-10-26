package org.example.common.enums;

import org.example.common.constant.MsgKeyConstant;
import org.example.common.util.MessageUtil;

public enum Code {
    UNKNOWN_ERROR(-1, MsgKeyConstant.UNKNOWN_ERROR),
    SUCCESS(0, MsgKeyConstant.SUCCESS),
    FAIL(500, MsgKeyConstant.FAIL),
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
