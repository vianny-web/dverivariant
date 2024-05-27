package com.vianny.dverivariant.enums.laminate;

public enum WaterResistance {
    YES("Да"),
    NO("Нет");

    private final String description;

    WaterResistance(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}