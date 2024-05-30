package com.vianny.dverivariant.dto.response.message;

import org.springframework.http.HttpStatus;


public class ProductMessage<T> {
    private HttpStatus httpStatus;
    private T products;

    public ProductMessage(HttpStatus httpStatus, T products) {
        this.httpStatus = httpStatus;
        this.products = products;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public T getProducts() {
        return products;
    }
}
