package com.vianny.dverivariant.exceptions.requiredException;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundRequiredException extends ResponseStatusException {

    public NotFoundRequiredException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
