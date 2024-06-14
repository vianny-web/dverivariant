package com.vianny.dverivariant.dto.response.product;

import java.util.HashMap;
import java.util.Objects;

public class ProductBriefDTO {
    private String id;
    private String article;
    private String name;
    private String description;
    private Integer price;
    private String pathImage;
    private HashMap<String, String> details;

    public ProductBriefDTO() {
    }

    public ProductBriefDTO(String id, String article, String name, String description, Integer price, String pathImage, HashMap<String, String> details) {
        this.id = id;
        this.article = article;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pathImage = pathImage;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getArticle() {
        return article;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBriefDTO that = (ProductBriefDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(article, that.article) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(pathImage, that.pathImage) && Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article, name, description, price, pathImage, details);
    }
}
