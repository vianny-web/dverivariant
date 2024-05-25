package com.vianny.dverivariant.enums.doors.interior;

public enum Glazing {
    WITHOUT_GLASS("Без стекла (глухое)"),
    WITH_MIRROR("С зеркалом"),
    WITH_GLASS("Со стеклом");

    private final String description;

    Glazing(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
