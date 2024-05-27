package com.vianny.dverivariant.enums.floors.laminate;

public enum BevelLaminate {
    NO_BEVEL("Без фаски"),
    BEVEL_4V("4V"),
    PAINTED_V4_BEVEL("V4 окрашенная");

    private final String description;

    BevelLaminate(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
