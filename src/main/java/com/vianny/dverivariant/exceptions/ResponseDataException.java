package com.vianny.dverivariant.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseDataException {
    private final HttpStatus status;
    private final String message;

    public ResponseDataException(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
