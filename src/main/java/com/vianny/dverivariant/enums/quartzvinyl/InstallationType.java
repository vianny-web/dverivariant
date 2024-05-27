package com.vianny.dverivariant.enums.quartzvinyl;

public enum InstallationType {
    CLICK("Замковый"),
    GLUE("Клеевой");

    private final String description;

    InstallationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
