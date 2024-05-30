package com.vianny.dverivariant.models.products.doors;

import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.doors.interior.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("doors")
public class InteriorDoor {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    private String idImage = UUID.randomUUID().toString();
    @Enumerated(value = EnumType.STRING)
    private TypeProducts type = TypeProducts.INTERIOR_DOOR;
    @Enumerated(value = EnumType.STRING)
    private Material material;
    @Enumerated(value = EnumType.STRING)
    private GlazingInterior glazing;
    @Enumerated(value = EnumType.STRING)
    private Modification modification;
    @Enumerated(value = EnumType.STRING)
    private Construction construction;
    @Enumerated(value = EnumType.STRING)
    private ManufacturerInterior manufacturer;

    public InteriorDoor() {
    }

    public InteriorDoor(String id, String name, String description, Integer price, String urlImage, String idImage, Material material, GlazingInterior glazing, Modification modification, Construction construction, ManufacturerInterior manufacturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.material = material;
        this.glazing = glazing;
        this.modification = modification;
        this.construction = construction;
        this.manufacturer = manufacturer;
    }

    public InteriorDoor(String name, String description, Integer price, String urlImage, String idImage, Material material, GlazingInterior glazing, Modification modification, Construction construction, ManufacturerInterior manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.material = material;
        this.glazing = glazing;
        this.modification = modification;
        this.construction = construction;
        this.manufacturer = manufacturer;
    }

    public InteriorDoor(String name, String description, Integer price, Material material, GlazingInterior glazing, Modification modification, Construction construction, ManufacturerInterior manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getIdImage() {
        return idImage;
    }

    public TypeProducts getType() {
        return type;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public GlazingInterior getGlazing() {
        return glazing;
    }

    public void setGlazing(GlazingInterior glazingInterior) {
        this.glazing = glazingInterior;
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

    public ManufacturerInterior getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerInterior manufacturerInterior) {
        this.manufacturer = manufacturerInterior;
    }
}
