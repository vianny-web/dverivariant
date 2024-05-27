package com.vianny.dverivariant.enums.doors.entrance;

public enum InstallationPlace {
    HOUSE("В дом"),
    APARTMENT("В квартиру");

    private final String description;

    InstallationPlace(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
