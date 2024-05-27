package com.vianny.dverivariant.enums.doors.entrance;

public enum Glazing {
    WITHOUT_GLASS("Без стекла (глухое)"),
    WITH_MIRROR("С зеркалом"),
    WITH_GLASS("Со стеклом"),
    DOUBLE_GLAZING("Стеклопакет");

    private final String description;

    Glazing(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}