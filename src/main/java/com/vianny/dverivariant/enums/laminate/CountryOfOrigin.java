package com.vianny.dverivariant.enums.laminate;

public enum CountryOfOrigin {
    RUSSIA("Россия"),
    BELARUS("Беларусь"),
    TURKEY("Турция"),
    CHINA("КНР");

    private final String description;

    CountryOfOrigin(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
