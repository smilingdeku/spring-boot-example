package org.example.module.sys.user.domain.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "{system.user.username-can-not-be-blank}")
    private String username;
    @NotBlank(message = "{system.user.password-can-not-be-blank}")
    private String password;

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
}
