package com.vianny.dverivariant.dto.response.product;

import com.vianny.dverivariant.enums.TypeProducts;

import java.util.HashMap;

public class ProductDetailsDTO {
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String pathImage;
    private TypeProducts typeProduct;
    private HashMap<String, String> details;

    public ProductDetailsDTO() {
    }

    public ProductDetailsDTO(String id, String name, String description, Integer price, String pathImage, TypeProducts typeProduct, HashMap<String, String> details) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pathImage = pathImage;
        this.typeProduct = typeProduct;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public String getPathImage() {
        return pathImage;
    }

    public TypeProducts getTypeProduct() {
        return typeProduct;
    }

    public HashMap<String, String> getDetails() {
        return details;
    }
}
