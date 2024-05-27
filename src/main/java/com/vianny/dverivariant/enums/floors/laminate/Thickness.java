package com.vianny.dverivariant.enums.floors.laminate;

public enum Thickness {
    THICKNESS_8MM("8 мм"),
    THICKNESS_10MM("10 мм"),
    THICKNESS_12MM("12 мм");

    private final String description;

    Thickness(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}