package com.vianny.dverivariant.dto.response.jwt;

import org.springframework.http.HttpStatus;

public class JwtToken {
    private HttpStatus httpStatus;
    private String jwtToken;

    public JwtToken(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.jwtToken = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}