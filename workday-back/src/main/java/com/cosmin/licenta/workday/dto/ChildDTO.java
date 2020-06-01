package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.GenderReferential;
import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDate;

public class ChildDTO {

    private Long id;

    private EmployeeDTO employee;

    private String lastName;

    private String firstName;

    private String personIdentifier;

    private LocalDate birthDate;

    private GenderReferential gender;

    private boolean worksInCompany;

    private byte [] attestingDocument;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPersonIdentifier() {
        return personIdentifier;
    }

    public void setPersonIdentifier(String personIdentifier) {
        this.personIdentifier = personIdentifier;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public GenderReferential getGender() {
        return gender;
    }

    public void setGender(GenderReferential gender) {
        this.gender = gender;
    }

    public boolean isWorksInCompany() {
        return worksInCompany;
    }

    public void setWorksInCompany(boolean worksInCompany) {
        this.worksInCompany = worksInCompany;
    }

    public byte[] getAttestingDocument() {
        return attestingDocument;
    }

    public void setAttestingDocument(byte[] attestingDocument) {
        this.attestingDocument = attestingDocument;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employee", employee)
                .add("lastName", lastName)
                .add("firstName", firstName)
                .add("personIdentifier", personIdentifier)
                .add("birthDate", birthDate)
                .add("gender", gender)
                .add("worksInCompany", worksInCompany)
                .add("attestingDocument", attestingDocument)
                .toString();
    }
}
