package com.vianny.dverivariant.enums.laminate;

public enum ClassType {
    CLASS_32(32),
    CLASS_33(33),
    CLASS_34(34);

    private final int value;

    ClassType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}