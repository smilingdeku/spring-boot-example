package org.example.module.system.user.domain.response;


public class TokenResponse {
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
