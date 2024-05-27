package com.vianny.dverivariant.models.products.doors;

import com.vianny.dverivariant.enums.doors.entrance.AdditionalProperties;
import com.vianny.dverivariant.enums.doors.entrance.Glazing;
import com.vianny.dverivariant.enums.doors.entrance.InstallationPlace;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("doors")
public class EntranceDoor {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    private String idImage = UUID.randomUUID().toString();
    @Enumerated(value = EnumType.STRING)
    private InstallationPlace installationPlace;
    @Enumerated(value = EnumType.STRING)
    private Glazing glazing;
    @Enumerated(value = EnumType.STRING)
    private AdditionalProperties additionalProperties;

    public EntranceDoor() {
    }

    public EntranceDoor(String id, String name, String description, Integer price, String urlImage, String idImage, InstallationPlace installationPlace, Glazing glazing, AdditionalProperties additionalProperties) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.installationPlace = installationPlace;
        this.glazing = glazing;
        this.additionalProperties = additionalProperties;
    }

    public EntranceDoor(String name, String description, Integer price, String urlImage, String idImage, InstallationPlace installationPlace, Glazing glazing, AdditionalProperties additionalProperties) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.installationPlace = installationPlace;
        this.glazing = glazing;
        this.additionalProperties = additionalProperties;
    }

    public EntranceDoor(String name, String description, Integer price, InstallationPlace installationPlace, Glazing glazing, AdditionalProperties additionalProperties) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.installationPlace = installationPlace;
        this.glazing = glazing;
        this.additionalProperties = additionalProperties;
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

    public InstallationPlace getInstallationPlace() {
        return installationPlace;
    }

    public void setInstallationPlace(InstallationPlace installationPlace) {
        this.installationPlace = installationPlace;
    }

    public Glazing getGlazing() {
        return glazing;
    }

    public void setGlazing(Glazing glazing) {
        this.glazing = glazing;
    }

    public AdditionalProperties getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(AdditionalProperties additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
