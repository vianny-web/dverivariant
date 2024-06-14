package com.vianny.dverivariant.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

public class SignUpRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    private String key;

    public SignUpRequest() {
    }

    public SignUpRequest(String login, String password, String key) {
        this.login = login;
        this.password = password;
        this.key = key;
    }

    public @NotBlank String getLogin() {
        return login;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }
}
