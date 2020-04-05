package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workday_academic_study")
public class AcademicStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "study_level_id")
    private StudyLevelReferential studyLevel;

    @Column(name = "educational_institution")
    private String educationalInstitution;

    @OneToOne
    @JoinColumn(name = "study_field_id")
    private StudyFieldReferential studyField;

    private String specialization;

    @OneToOne
    @JoinColumn(name = "country_id")
    private CountryReferential country;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    private boolean finalized;

    private byte[] diploma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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
}
