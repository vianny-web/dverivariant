package com.vianny.dverivariant.enums.floors.quartzvinyl;

public enum BevelQuartzvinyl {
    NO_BEVEL("Без фаски"),
    MICRO_BEVEL("Микро"),
    V4_BEVEL("V4");

    private final String description;

    BevelQuartzvinyl(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}