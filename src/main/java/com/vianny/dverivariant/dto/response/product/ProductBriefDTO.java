package com.vianny.dverivariant.dto.response.product;

import com.vianny.dverivariant.enums.TypeProducts;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ProductBriefDTO {
    private String id;
    private String name;
    private Integer price;
    private String urlImage;
    @Enumerated(value = EnumType.STRING)
    private TypeProducts typeProduct;

    public ProductBriefDTO(String id, String name, Integer price, String urlImage, TypeProducts typeProduct) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
        this.typeProduct = typeProduct;
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

    public TypeProducts getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProducts typeProduct) {
        this.typeProduct = typeProduct;
    }
}
