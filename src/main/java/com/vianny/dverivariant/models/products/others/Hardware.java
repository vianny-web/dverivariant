package com.vianny.dverivariant.models.products.others;

import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.others.HardwareType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document("products")
public class Hardware {
    @Id
    private String id;
    private String article;
    private String name;
    private String description;
    private Integer price;
    @Enumerated(value = EnumType.STRING)
    private String pathImage = TypeProducts.HARDWARE + "/" + UUID.randomUUID();
    @Enumerated(value = EnumType.STRING)
    private TypeProducts type = TypeProducts.HARDWARE;
    @Enumerated(value = EnumType.STRING)
    private HardwareType hardwareType;

    public Hardware() {
    }

    public Hardware(String id, String article, String name, String description, Integer price, HardwareType hardwareType, String pathImage) {
        this.id = id;
        this.article = article;
        this.name = name;
        this.description = description;
        this.price = price;
        this.hardwareType = hardwareType;
        this.pathImage = pathImage;
    }

    public Hardware(String article, String name, String description, Integer price, HardwareType hardwareType) {
        this.article = article;
        this.name = name;
        this.description = description;
        this.price = price;
        this.hardwareType = hardwareType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
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

    public String getPathImage() {
        return pathImage;
    }

    public TypeProducts getType() {
        return type;
    }

    public HardwareType getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(HardwareType hardwareType) {
        this.hardwareType = hardwareType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return Objects.equals(id, hardware.id) && Objects.equals(article, hardware.article) && Objects.equals(name, hardware.name) && Objects.equals(description, hardware.description) && Objects.equals(price, hardware.price) && Objects.equals(pathImage, hardware.pathImage) && type == hardware.type && hardwareType == hardware.hardwareType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article, name, description, price, pathImage, type, hardwareType);
    }
}
