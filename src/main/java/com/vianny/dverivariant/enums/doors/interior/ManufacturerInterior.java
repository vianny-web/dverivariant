package com.vianny.dverivariant.enums.doors.interior;

public enum ManufacturerInterior {
    OPTIMA_PORTE("Optima Porte"),
    UBERTURE("Uberture"),
    OKA("Ока"),
    FLY_DOORS("FlyDoors"),
    ART_GAMMA("ArtGamma"),
    QUEST_DOORS("QuestDoors"),
    WEST_STYLE("WestStyle"),
    EL_PORTA("elPORTA"),
    VFD("ВФД"),
    TANDOR("Tandor"),
    ECO_STYLE("Эко-Стиль"),
    DIODOR("ДИОдор"),
    DARIANO("Dariano"),
    LEGEND("Легенда");

    private final String description;

    ManufacturerInterior(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}