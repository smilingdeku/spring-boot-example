package org.example.module.system.user.domain.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "{system.user.username-can-not-be-blank}")
    private String username;
    @NotBlank(message = "{system.user.password-can-not-be-blank}")
    private String password;
    @NotBlank(message = "{system.user.captcha-can-not-be-blank}")
    private String captchaKey;
    @NotBlank(message = "{system.user.captcha-can-not-be-blank}")
    private String captcha;

    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
