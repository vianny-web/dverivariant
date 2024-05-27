package com.vianny.dverivariant.enums.others;

public enum HardwareType {
    HANDLES("Ручки"),
    HINGES("Петли"),
    MORTISE_LOCKS("Врезные замки"),
    LATCHES("Защелки"),
    COVERS("Накладки"),
    FIXTURES("Фиксаторы"),
    DOOR_STOP("Ограничитель дверной");

    private final String description;

    HardwareType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
