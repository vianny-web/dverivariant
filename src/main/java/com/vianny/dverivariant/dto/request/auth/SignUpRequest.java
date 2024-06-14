package com.vianny.dverivariant.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpRequest that = (SignUpRequest) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, key);
    }
}
