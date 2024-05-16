package com.vianny.dverivariant.exceptions.requiredException;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UnregisteredRequiredException extends ResponseStatusException {
    public UnregisteredRequiredException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
