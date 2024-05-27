package com.vianny.dverivariant.enums.floors.quartzvinyl;

public enum Base {
    SPC("SPC"),
    QUARTZ_VINYL("Кварц-винил"),
    ABA("ABA"),
    WPC("WPC");

    private final String description;

    Base(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}