package com.vianny.dverivariant.enums.floors.quartzvinyl;

public enum ManufacturerQuartzvinyl {
    TARKETT("Tarkett"),
    ART("Art"),
    KRONOSPAN("Kronospan"),
    VESTERHOF("Vesterhof"),
    YILDIZENTEGRE("Yildizentegre"),
    AGT("AGT"),
    DESIGN_COLLECTION("Design Collection"),
    CAMSEN("Camsen");

    private final String description;

    ManufacturerQuartzvinyl(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
