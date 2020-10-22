package org.example.common;


import java.io.Serializable;

public class LoginResponse implements Serializable {
    private static final long serialVersionUID = 1L;

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
