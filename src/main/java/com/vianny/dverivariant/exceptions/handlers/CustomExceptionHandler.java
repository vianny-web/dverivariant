package com.vianny.dverivariant.exceptions.handlers;

import com.vianny.dverivariant.exceptions.ResponseDataException;
import com.vianny.dverivariant.exceptions.requiredException.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UnauthorizedRequiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDataException handlerUnauthorizedErrorException (UnauthorizedRequiredException exception) {
        return new ResponseDataException((HttpStatus) exception.getStatusCode(), exception.getReason());
    }

    @ExceptionHandler(UnregisteredRequiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDataException handlerUnregisteredErrorException (UnregisteredRequiredException exception) {
        return new ResponseDataException(HttpStatus.BAD_REQUEST, exception.getReason());
    }

    @ExceptionHandler(NotFoundRequiredException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDataException handlerNotFoundErrorException (NotFoundRequiredException exception) {
        return new ResponseDataException(HttpStatus.NOT_FOUND, exception.getReason());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ServerErrorRequiredException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDataException handlerServerErrorException (ServerErrorRequiredException exception) {
        return new ResponseDataException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
