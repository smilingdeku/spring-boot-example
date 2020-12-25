package org.example.common.enums;

import org.example.common.constant.MsgKeyConstant;
import org.example.common.util.MessageUtil;

/**
 * 返回码枚举
 */
public enum ResultCode {
    /**
     * 请求成功
     */
    SUCCESS(0, MsgKeyConstant.SUCCESS),
    /**
     * 操作失败
     */
    FAILURE(-1, MsgKeyConstant.FAIL),
    ;

    ResultCode(Integer code, String msgKey) {
        this.code = code;
        this.msgKey = msgKey;
    }

    /**
     * 返回码
     */
    private final Integer code;
    /**
     * 返回消息 KEY
     */
    private final String msgKey;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return MessageUtil.get(msgKey);
    }
}
