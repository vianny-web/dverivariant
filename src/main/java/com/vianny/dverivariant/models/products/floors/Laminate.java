package com.vianny.dverivariant.models.products.floors;

import com.vianny.dverivariant.enums.floors.laminate.Bevel;
import com.vianny.dverivariant.enums.floors.laminate.ClassType;
import com.vianny.dverivariant.enums.floors.laminate.CountryOfOrigin;
import com.vianny.dverivariant.enums.floors.laminate.Thickness;
import com.vianny.dverivariant.enums.floors.laminate.WaterResistance;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("floors")
public class Laminate {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    private String idImage = UUID.randomUUID().toString();
    @Enumerated(value = EnumType.STRING)
    private ClassType classType;
    @Enumerated(value = EnumType.STRING)
    private Thickness thickness;
    @Enumerated(value = EnumType.STRING)
    private WaterResistance waterResistance;
    @Enumerated(value = EnumType.STRING)
    private Bevel bevel;
    @Enumerated(value = EnumType.STRING)
    private CountryOfOrigin countryOfOrigin;

    public Laminate() {
    }

    public Laminate(String id, String name, String description, Integer price, String urlImage, String idImage, ClassType classType, Thickness thickness, WaterResistance waterResistance, Bevel bevel, CountryOfOrigin countryOfOrigin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.classType = classType;
        this.thickness = thickness;
        this.waterResistance = waterResistance;
        this.bevel = bevel;
        this.countryOfOrigin = countryOfOrigin;
    }

    public Laminate(String name, String description, Integer price, String urlImage, String idImage, ClassType classType, Thickness thickness, WaterResistance waterResistance, Bevel bevel, CountryOfOrigin countryOfOrigin) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.classType = classType;
        this.thickness = thickness;
        this.waterResistance = waterResistance;
        this.bevel = bevel;
        this.countryOfOrigin = countryOfOrigin;
    }

    public Laminate(String name, String description, Integer price, ClassType classType, Thickness thickness, WaterResistance waterResistance, Bevel bevel, CountryOfOrigin countryOfOrigin) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.classType = classType;
        this.thickness = thickness;
        this.waterResistance = waterResistance;
        this.bevel = bevel;
        this.countryOfOrigin = countryOfOrigin;
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

    public Bevel getBevel() {
        return bevel;
    }

    public void setBevel(Bevel bevel) {
        this.bevel = bevel;
    }

    public CountryOfOrigin getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(CountryOfOrigin countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
}
