package com.vianny.dverivariant.models.products.doors;

import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.doors.entrance.AdditionalProperties;
import com.vianny.dverivariant.enums.doors.entrance.GlazingEntrance;
import com.vianny.dverivariant.enums.doors.entrance.InstallationPlace;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("products")
public class EntranceDoor {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer price;
    @Enumerated(value = EnumType.STRING)
    private String pathImage = TypeProducts.ENTRANCE_DOOR + "/" + UUID.randomUUID();
    @Enumerated(value = EnumType.STRING)
    private TypeProducts type = TypeProducts.ENTRANCE_DOOR;
    @Enumerated(value = EnumType.STRING)
    private InstallationPlace installationPlace;
    @Enumerated(value = EnumType.STRING)
    private GlazingEntrance glazingEntrance;
    @Enumerated(value = EnumType.STRING)
    private AdditionalProperties additionalProperties;

    public EntranceDoor() {
    }

    public EntranceDoor(String id, String name, String description, Integer price, String pathImage, InstallationPlace installationPlace, GlazingEntrance glazingEntrance, AdditionalProperties additionalProperties) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pathImage = pathImage;
        this.installationPlace = installationPlace;
        this.glazingEntrance = glazingEntrance;
        this.additionalProperties = additionalProperties;
    }

    public EntranceDoor(String name, String description, Integer price, InstallationPlace installationPlace, GlazingEntrance glazingEntrance, AdditionalProperties additionalProperties) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.installationPlace = installationPlace;
        this.glazingEntrance = glazingEntrance;
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

    public TypeProducts getType() {
        return type;
    }

    public String getPathImage() {
        return pathImage;
    }

    public InstallationPlace getInstallationPlace() {
        return installationPlace;
    }

    public void setInstallationPlace(InstallationPlace installationPlace) {
        this.installationPlace = installationPlace;
    }

    public GlazingEntrance getGlazing() {
        return glazingEntrance;
    }

    public void setGlazing(GlazingEntrance glazingEntrance) {
        this.glazingEntrance = glazingEntrance;
    }

    public AdditionalProperties getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(AdditionalProperties additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
