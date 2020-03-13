package com.cosmin.licenta.workday.util;

public enum DepartmentReferentialEnum {
    SSCM_CORP("SSCM/CORP"),
    SSCM_FIN("SSCM/FIN"),
    SSCM_HR("SSCM/HR"),
    SSCM_ITIM("SSCM/ITIM");

    private String label;

    DepartmentReferentialEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



