package com.vianny.dverivariant.enums.doors.entrance;

public enum AdditionalProperties {
    REPLACEABLE_PANEL("Сменная панель"),
    THERMAL_BREAK("Терморазрыв"),
    WITH_FORGING("С ковкой"),
    VINORIT("Винорит");

    private final String description;

    AdditionalProperties(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
