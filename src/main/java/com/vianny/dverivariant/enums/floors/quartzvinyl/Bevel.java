package com.vianny.dverivariant.enums.floors.quartzvinyl;

public enum Bevel {
    NO_BEVEL("Без фаски"),
    MICRO_BEVEL("Микро"),
    V4_BEVEL("V4");

    private final String description;

    Bevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}