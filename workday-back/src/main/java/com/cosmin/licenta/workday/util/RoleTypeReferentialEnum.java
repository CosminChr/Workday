package com.cosmin.licenta.workday.util;

public enum RoleTypeReferentialEnum {
    ADMIN("Admin"),
    EMPLOYEE("Employee"),
    MANAGER("Manager");

    private String label;

    RoleTypeReferentialEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
