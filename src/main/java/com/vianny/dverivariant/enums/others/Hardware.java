package com.vianny.dverivariant.enums.others;

public enum Hardware {
    HANDLES("Ручки"),
    HINGES("Петли"),
    MORTISE_LOCKS("Врезные замки"),
    LATCHES("Защелки"),
    COVERS("Накладки"),
    FIXTURES("Фиксаторы"),
    DOOR_STOP("Ограничитель дверной");

    private final String description;

    Hardware(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
