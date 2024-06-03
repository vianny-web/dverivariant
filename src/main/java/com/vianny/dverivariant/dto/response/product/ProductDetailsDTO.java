package com.vianny.dverivariant.dto.response.product;

import java.util.HashMap;

public class ProductDetailsDTO {
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String pathImage;
    private HashMap<String, String> details;

    public ProductDetailsDTO() {
    }

    public ProductDetailsDTO(String id, String name, String description, Integer price, String pathImage, HashMap<String, String> details) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pathImage = pathImage;
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

    public HashMap<String, String> getDetails() {
        return details;
    }
}
