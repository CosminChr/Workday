package com.cosmin.licenta.workday.util;

public enum GenderEnum {
    MALE("Male"),
    FEMALE("Female");

    private String label;

    GenderEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
