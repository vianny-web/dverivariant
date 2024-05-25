package com.vianny.dverivariant.enums.doors.interior;

public enum Construction {
    SHIELD("Щитовая"),
    TSARGOVAYA("Царговая"),
    ALUMINUM_EDGE("С алюминиевой кромкой"),
    PLASTIC_EDGE("С пластиковой кромкой"),
    BAGET("Багетные");

    private final String description;

    Construction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}