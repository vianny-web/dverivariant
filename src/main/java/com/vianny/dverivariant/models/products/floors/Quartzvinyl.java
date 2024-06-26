package com.vianny.dverivariant.models.products.floors;

import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.floors.quartzvinyl.Base;
import com.vianny.dverivariant.enums.floors.quartzvinyl.BevelQuartzvinyl;
import com.vianny.dverivariant.enums.floors.quartzvinyl.InstallationType;
import com.vianny.dverivariant.enums.floors.quartzvinyl.ManufacturerQuartzvinyl;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document("products")
public class Quartzvinyl {
    @Id
    private String id;
    private String article;
    private String name;
    private String description;
    private Integer price;
    @Enumerated(value = EnumType.STRING)
    private String pathImage = TypeProducts.QUARTZVINYL + "/" + UUID.randomUUID();
    @Enumerated(value = EnumType.STRING)
    private TypeProducts type = TypeProducts.QUARTZVINYL;
    @Enumerated(value = EnumType.STRING)
    private Base base;
    @Enumerated(value = EnumType.STRING)
    private InstallationType installationType;
    @Enumerated(value = EnumType.STRING)
    private BevelQuartzvinyl bevelQuartzvinyl;
    @Enumerated(value = EnumType.STRING)
    private ManufacturerQuartzvinyl manufacturerQuartzvinyl;

    public Quartzvinyl() {
    }

    public Quartzvinyl(String id, String article, String name, String description, Integer price, String pathImage, Base base, InstallationType installationType, BevelQuartzvinyl bevelQuartzvinyl, ManufacturerQuartzvinyl manufacturerQuartzvinyl) {
        this.id = id;
        this.article = article;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pathImage = pathImage;
        this.base = base;
        this.installationType = installationType;
        this.bevelQuartzvinyl = bevelQuartzvinyl;
        this.manufacturerQuartzvinyl = manufacturerQuartzvinyl;
    }

    public Quartzvinyl(String article, String name, String description, Integer price, Base base, InstallationType installationType, BevelQuartzvinyl bevelQuartzvinyl, ManufacturerQuartzvinyl manufacturerQuartzvinyl) {
        this.article = article;
        this.name = name;
        this.description = description;
        this.price = price;
        this.base = base;
        this.installationType = installationType;
        this.bevelQuartzvinyl = bevelQuartzvinyl;
        this.manufacturerQuartzvinyl = manufacturerQuartzvinyl;
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

    public BevelQuartzvinyl getBevel() {
        return bevelQuartzvinyl;
    }

    public void setBevel(BevelQuartzvinyl bevelQuartzvinyl) {
        this.bevelQuartzvinyl = bevelQuartzvinyl;
    }

    public ManufacturerQuartzvinyl getManufacturer() {
        return manufacturerQuartzvinyl;
    }

    public void setManufacturer(ManufacturerQuartzvinyl manufacturerQuartzvinyl) {
        this.manufacturerQuartzvinyl = manufacturerQuartzvinyl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quartzvinyl that = (Quartzvinyl) o;
        return Objects.equals(id, that.id) && Objects.equals(article, that.article) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(pathImage, that.pathImage) && type == that.type && base == that.base && installationType == that.installationType && bevelQuartzvinyl == that.bevelQuartzvinyl && manufacturerQuartzvinyl == that.manufacturerQuartzvinyl;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article, name, description, price, pathImage, type, base, installationType, bevelQuartzvinyl, manufacturerQuartzvinyl);
    }
}
