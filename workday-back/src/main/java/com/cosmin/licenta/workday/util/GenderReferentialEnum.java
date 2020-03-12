package com.cosmin.licenta.workday.util;

public enum GenderReferentialEnum {
    MALE("Male"),
    FEMALE("Female");

    private String label;

    GenderReferentialEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
