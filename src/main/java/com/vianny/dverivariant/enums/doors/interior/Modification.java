package com.vianny.dverivariant.enums.doors.interior;

public enum Modification {
    STANDARD_OPENING("Стандартное открывание"),
    SLIDING_DOORS("Раздвижные двери"),
    HIDDEN_DOORS("Скрытые двери");

    private final String description;

    Modification(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}