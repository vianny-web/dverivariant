package com.vianny.dverivariant.enums.doors.interior;

public enum Material {
    MASSIV("Массив"),
    NATURAL_VENEER("Натуральный шпон"),
    SOFT_TOUCH("Покрытие Soft Touch"),
    ECO_VENEER("Экошпон"),
    ENAMEL("Эмаль");

    private final String description;

    Material(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}