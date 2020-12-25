package org.example.module.system.user.domain.response;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "请求令牌")
    private String token;

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
