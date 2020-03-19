package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class PartnerDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String personIdentifier;

    private LocalDate birthDate;

    private boolean worksInCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isWorksInCompany() {
        return worksInCompany;
    }

    public void setWorksInCompany(boolean worksInCompany) {
        this.worksInCompany = worksInCompany;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("lastName", lastName)
                .add("firstName", firstName)
                .add("personIdentifier", personIdentifier)
                .add("birthDate", birthDate)
                .add("worksInCompany", worksInCompany)
                .toString();
    }
}
