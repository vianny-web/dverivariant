package com.vianny.dverivariant.enums.floors.laminate;

public enum ClassType {
    CLASS_32("32"),
    CLASS_33("33"),
    CLASS_34("34");

    private final String value;

    ClassType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}