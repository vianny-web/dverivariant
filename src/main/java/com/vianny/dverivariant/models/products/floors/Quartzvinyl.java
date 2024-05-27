package com.vianny.dverivariant.models.products.floors;

import com.vianny.dverivariant.enums.floors.quartzvinyl.Base;
import com.vianny.dverivariant.enums.floors.quartzvinyl.Bevel;
import com.vianny.dverivariant.enums.floors.quartzvinyl.InstallationType;
import com.vianny.dverivariant.enums.floors.quartzvinyl.Manufacturer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("floors")
public class Quartzvinyl {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    private String idImage = UUID.randomUUID().toString();
    @Enumerated(value = EnumType.STRING)
    private Base base;
    @Enumerated(value = EnumType.STRING)
    private InstallationType installationType;
    @Enumerated(value = EnumType.STRING)
    private Bevel bevel;
    @Enumerated(value = EnumType.STRING)
    private Manufacturer manufacturer;

    public Quartzvinyl() {
    }

    public Quartzvinyl(String id, String name, String description, Integer price, String urlImage, String idImage, Base base, InstallationType installationType, Bevel bevel, Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.base = base;
        this.installationType = installationType;
        this.bevel = bevel;
        this.manufacturer = manufacturer;
    }

    public Quartzvinyl(String name, String description, Integer price, String urlImage, String idImage, Base base, InstallationType installationType, Bevel bevel, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.idImage = idImage;
        this.base = base;
        this.installationType = installationType;
        this.bevel = bevel;
        this.manufacturer = manufacturer;
    }

    public Quartzvinyl(String name, String description, Integer price, Base base, InstallationType installationType, Bevel bevel, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.base = base;
        this.installationType = installationType;
        this.bevel = bevel;
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

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public InstallationType getInstallationType() {
        return installationType;
    }

    public void setInstallationType(InstallationType installationType) {
        this.installationType = installationType;
    }

    public Bevel getBevel() {
        return bevel;
    }

    public void setBevel(Bevel bevel) {
        this.bevel = bevel;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
