package org.example.module.system.user.domain.response;


import java.io.Serializable;

public class TokenResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

    public TokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
