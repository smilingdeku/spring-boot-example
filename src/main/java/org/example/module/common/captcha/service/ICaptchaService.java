package org.example.module.common.captcha.service;

/**
 * @author linzhaoming
 * @since 2020-12-18
 **/
public interface ICaptchaService {

    /**
     * 生成验证码
     *
     * @param type 验证码类型
     * @param uuid UUID
     * @return String 图片 Base64 编码
     */
    String generateCaptcha(Integer type, String uuid);

}
