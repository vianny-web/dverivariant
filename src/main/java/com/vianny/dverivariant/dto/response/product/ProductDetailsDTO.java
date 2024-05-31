package com.vianny.dverivariant.dto.response.product;

import com.vianny.dverivariant.enums.TypeProducts;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.HashMap;
import java.util.UUID;

public class ProductDetailsDTO {
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    private String idImage = UUID.randomUUID().toString();
    @Enumerated(value = EnumType.STRING)
    private TypeProducts typeProduct;
    private HashMap<String, String> details;

    public ProductDetailsDTO() {
    }

    public ProductDetailsDTO(String id, String name, String description, Integer price, String urlImage, String idImage, TypeProducts typeProduct, HashMap<String, String> details) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.typeProduct = typeProduct;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public TypeProducts getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProducts typeProduct) {
        this.typeProduct = typeProduct;
    }

    public HashMap<String, String> getDetails() {
        return details;
    }

    public void setDetails(HashMap<String, String> details) {
        this.details = details;
    }
}
