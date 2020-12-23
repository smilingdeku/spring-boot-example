package org.example.module.common.captcha.enums;

/**
 * @author linzhaoming
 * @since 2020-12-16
 **/
public enum CaptchaType {

    /**
     * 后台登录验证码
     */
    ADMIN(1, "string:captcha:admin:%s");

    private final Integer value;
    private final String keyPattern;

    CaptchaType(Integer value, String keyPattern) {
        this.value = value;
        this.keyPattern = keyPattern;
    }

    public Integer getValue() {
        return value;
    }

    public String getKeyPattern() {
        return keyPattern;
    }

    public static CaptchaType get(Integer value) {
        for (CaptchaType captchaType : CaptchaType.values()) {
            if (captchaType.getValue().equals(value)) {
                return captchaType;
            }
        }
        return null;
    }
}
