package org.example.module.common.captcha.enums;

/**
 * @author linzhaoming
 * @since 2020-12-16
 **/
public enum CaptchaType {

    /**
     * 后台登录验证码
     */
    ADMIN(1, "captcha:admin:");

    private final Integer value;
    private final String keyPrefix;

    CaptchaType(Integer value, String keyPrefix) {
        this.value = value;
        this.keyPrefix = keyPrefix;
    }

    public Integer getValue() {
        return value;
    }

    public String getKeyPrefix() {
        return keyPrefix;
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
