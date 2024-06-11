package com.vianny.dverivariant.models.products.floors;

import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.floors.laminate.BevelLaminate;
import com.vianny.dverivariant.enums.floors.laminate.ClassType;
import com.vianny.dverivariant.enums.floors.laminate.CountryOfOrigin;
import com.vianny.dverivariant.enums.floors.laminate.Thickness;
import com.vianny.dverivariant.enums.floors.laminate.WaterResistance;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("products")
public class Laminate {
    @Id
    private String id;
    private String article;
    private String name;
    private String description;
    private Integer price;
    @Enumerated(value = EnumType.STRING)
    private String pathImage = TypeProducts.LAMINATE + "/" + UUID.randomUUID();
    @Enumerated(value = EnumType.STRING)
    private TypeProducts type = TypeProducts.LAMINATE;
    @Enumerated(value = EnumType.STRING)
    private ClassType classType;
    @Enumerated(value = EnumType.STRING)
    private Thickness thickness;
    @Enumerated(value = EnumType.STRING)
    private WaterResistance waterResistance;
    @Enumerated(value = EnumType.STRING)
    private BevelLaminate bevelLaminate;
    @Enumerated(value = EnumType.STRING)
    private CountryOfOrigin countryOfOrigin;

    public Laminate() {
    }

    public Laminate(String id, String article, String name, String description, Integer price, String pathImage, ClassType classType, Thickness thickness, WaterResistance waterResistance, BevelLaminate bevelLaminate, CountryOfOrigin countryOfOrigin) {
        this.id = id;
        this.article = article;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pathImage = pathImage;
        this.classType = classType;
        this.thickness = thickness;
        this.waterResistance = waterResistance;
        this.bevelLaminate = bevelLaminate;
        this.countryOfOrigin = countryOfOrigin;
    }

    public Laminate(String article, String name, String description, Integer price, ClassType classType, Thickness thickness, WaterResistance waterResistance, BevelLaminate bevelLaminate, CountryOfOrigin countryOfOrigin) {
        this.article = article;
        this.name = name;
        this.description = description;
        this.price = price;
        this.classType = classType;
        this.thickness = thickness;
        this.waterResistance = waterResistance;
        this.bevelLaminate = bevelLaminate;
        this.countryOfOrigin = countryOfOrigin;
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

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Thickness getThickness() {
        return thickness;
    }

    public void setThickness(Thickness thickness) {
        this.thickness = thickness;
    }

    public WaterResistance getWaterResistance() {
        return waterResistance;
    }

    public void setWaterResistance(WaterResistance waterResistance) {
        this.waterResistance = waterResistance;
    }

    public BevelLaminate getBevel() {
        return bevelLaminate;
    }

    public void setBevel(BevelLaminate bevelLaminate) {
        this.bevelLaminate = bevelLaminate;
    }

    public CountryOfOrigin getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(CountryOfOrigin countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
}
