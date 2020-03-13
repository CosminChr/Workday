package com.cosmin.licenta.workday.util;

public enum HolidayReferentialEnum {

    CURRENT_LEAVE("Concediu de odihnă"),
    UNPAID_LEAVE("Concediu fără plată"),
    ABSENCES("Absențe nemotivate"),
    MEDICAL_LEAVE("Concediu medical"),
    DELEGATION("Delegație"),
    PROFESSIONAL_TRAINING("Formare profesională"),
    BLOOD_DONATION("Donare sânge"),
    BIRTH_DAYS("Naștere copil"),
    DEATH_DAYS("Deces"),
    CHILD_WEDDING_DAYS("Căsătorie copil"),
    WEDDING_DAYS("Căsătorie"),
    GEOGRAPHICAL_MOBILITY("Mobilitate geografică"),
    HOLIDAYS("Sărbătoare"),
    CHILD_CARE("Îngrijirea sănătății copilului"),
    STRIKE("Grevă"),
    SICK_DAYS("Necesitate medicală");

    private String label;

    HolidayReferentialEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
