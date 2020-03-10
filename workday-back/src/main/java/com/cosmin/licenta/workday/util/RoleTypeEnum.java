package com.cosmin.licenta.workday.util;

public enum RoleTypeEnum {
    ADMIN("Admin"),
    EMPLOYEE("Employee"),
    MANAGER("Manager");

    private String label;

    RoleTypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
