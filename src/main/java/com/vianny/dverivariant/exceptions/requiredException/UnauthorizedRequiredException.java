package com.vianny.dverivariant.exceptions.requiredException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedRequiredException extends ResponseStatusException {

    public UnauthorizedRequiredException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
