package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.CountryReferential;
import com.cosmin.licenta.workday.entity.StudyFieldReferential;
import com.cosmin.licenta.workday.entity.StudyLevelReferential;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class AcademicStudyDTO {

    private Long id;

    private EmployeeDTO employee;

    private StudyLevelReferential studyLevel;//2

    private String educationalInstitution;//1

    private StudyFieldReferential studyField;//1

    private String specialization;//1

    private CountryReferential country;//3

    private LocalDate fromDate;//2

    private LocalDate toDate;//2

    private boolean finalized;//3

    private byte[] diploma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public StudyLevelReferential getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevelReferential studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public StudyFieldReferential getStudyField() {
        return studyField;
    }

    public void setStudyField(StudyFieldReferential studyField) {
        this.studyField = studyField;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public CountryReferential getCountry() {
        return country;
    }

    public void setCountry(CountryReferential country) {
        this.country = country;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }

    public byte[] getDiploma() {
        return diploma;
    }

    public void setDiploma(byte[] diploma) {
        this.diploma = diploma;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employee", employee)
                .add("studyLevel", studyLevel)
                .add("educationalInstitution", educationalInstitution)
                .add("studyField", studyField)
                .add("specialization", specialization)
                .add("country", country)
                .add("fromDate", fromDate)
                .add("toDate", toDate)
                .add("finalized", finalized)
                .add("diploma", diploma)
                .toString();
    }
}
