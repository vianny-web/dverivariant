package com.vianny.dverivariant.models;

import com.vianny.dverivariant.enums.doors.interior.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("interior_door")
public class InteriorDoor {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    @Enumerated(value = EnumType.STRING)
    private Material material;
    @Enumerated(value = EnumType.STRING)
    private Glazing glazing;
    @Enumerated(value = EnumType.STRING)
    private Modification modification;
    @Enumerated(value = EnumType.STRING)
    private Construction construction;
    @Enumerated(value = EnumType.STRING)
    private Manufacturer manufacturer;

    public InteriorDoor(String name, String description, Integer price, String urlImage, Material material, Glazing glazing, Modification modification, Construction construction, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.material = material;
        this.glazing = glazing;
        this.modification = modification;
        this.construction = construction;
        this.manufacturer = manufacturer;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Glazing getGlazing() {
        return glazing;
    }

    public void setGlazing(Glazing glazing) {
        this.glazing = glazing;
    }

    public Modification getModification() {
        return modification;
    }

    public void setModification(Modification modification) {
        this.modification = modification;
    }

    public Construction getConstruction() {
        return construction;
    }

    public void setConstruction(Construction construction) {
        this.construction = construction;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
