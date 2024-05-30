package com.vianny.dverivariant.dto.response.message;

import org.springframework.http.HttpStatus;


public class ProductMessage<T> {
    private HttpStatus httpStatus;
    private T product;

    public ProductMessage(HttpStatus httpStatus, T product) {
        this.httpStatus = httpStatus;
        this.product = product;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public T getProduct() {
        return product;
    }
}
