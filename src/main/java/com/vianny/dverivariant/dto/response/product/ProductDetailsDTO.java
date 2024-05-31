package com.vianny.dverivariant.dto.response.product;

import com.vianny.dverivariant.enums.TypeProducts;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.HashMap;

public class ProductDetailsDTO {
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    @Enumerated(value = EnumType.STRING)
    private TypeProducts typeProduct;
    private HashMap<String, String> details;

    public ProductDetailsDTO() {
    }

    public ProductDetailsDTO(String id, String name, String description, Integer price, String urlImage, TypeProducts typeProduct, HashMap<String, String> details) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
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

    public String getUrlImage() {
        return urlImage;
    }

    public TypeProducts getTypeProduct() {
        return typeProduct;
    }

    public HashMap<String, String> getDetails() {
        return details;
    }
}
