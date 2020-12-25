package org.example.module.common.captcha.domain.response;

import io.swagger.annotations.ApiModelProperty;
import org.example.common.util.JsonUtil;

/**
 * @author linzhaoming
 * @since 2020-12-16
 **/
public class CaptchaResponse {

    @ApiModelProperty("验证码 KEY")
    private String key;

    @ApiModelProperty("验证码图片")
    private String image;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
