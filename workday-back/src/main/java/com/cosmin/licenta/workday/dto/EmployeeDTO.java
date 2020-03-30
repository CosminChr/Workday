package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.*;
import com.google.common.base.MoreObjects;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class EmployeeDTO {

    private Long id;


    @Size(max = 30)
    private String username;

    @Size(max = 30)
    private String email;

    @Size(max = 30)
    private String password;

    @Size(max = 40)
    private String lastName;

    @Size(max = 40)
    private String firstName;

    private ReferentialDTO gender;

    @Size(max = 40)
    private String birthPlace;

    @Size(max = 13)
    private String personIdentifier;

    private LocalDate birthDate;

    @Size(max = 40)
    private String birthName;

    @Size(max = 10)
    private String homePhoneNumber;

    @Size(max = 10)
    private String mobilePhoneNumber;

    private ReferentialDTO jobPosition;

    @Size(max = 30)
    private String entity;

    @Size(max = 30)
    private String location;

    private ReferentialDTO department;

    private Boolean ITDeduction;

    private LocalDate joiningDate;

    private LocalDate currentPositionStartingDate;

    private ReferentialDTO nationality;

    private Set<ReferentialDTO> roles = new HashSet<>();

    private Set<AcademicStudy> academicStudies;

    private Set<Address> addresses;

    private Set<BankAccount> bankAccounts;

    private Set<Child> children;

    private Set<Citizenship> citizenships;

    private Set<Holiday> holidays;

    private Set<IdentityDocument> identityDocuments;

    private Set<Language> languages;

    private MaritalStatus maritalStatus;

    private Partner partner;

    private Set<PreviousJob> previousJobs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public ReferentialDTO getGender() {
        return gender;
    }

    public void setGender(ReferentialDTO gender) {
        this.gender = gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
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

    public String getBirthName() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public ReferentialDTO getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(ReferentialDTO jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ReferentialDTO getDepartment() {
        return department;
    }

    public void setDepartment(ReferentialDTO department) {
        this.department = department;
    }

    public Boolean getITDeduction() {
        return ITDeduction;
    }

    public void setITDeduction(Boolean ITDeduction) {
        this.ITDeduction = ITDeduction;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getCurrentPositionStartingDate() {
        return currentPositionStartingDate;
    }

    public void setCurrentPositionStartingDate(LocalDate currentPositionStartingDate) {
        this.currentPositionStartingDate = currentPositionStartingDate;
    }

    public ReferentialDTO getNationality() {
        return nationality;
    }

    public void setNationality(ReferentialDTO nationality) {
        this.nationality = nationality;
    }

    public Set<ReferentialDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<ReferentialDTO> roles) {
        this.roles = roles;
    }


    public Set<AcademicStudy> getAcademicStudies() {
        return academicStudies;
    }

    public void setAcademicStudies(Set<AcademicStudy> academicStudies) {
        this.academicStudies = academicStudies;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }

    public Set<Citizenship> getCitizenships() {
        return citizenships;
    }

    public void setCitizenships(Set<Citizenship> citizenships) {
        this.citizenships = citizenships;
    }

    public Set<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(Set<Holiday> holidays) {
        this.holidays = holidays;
    }

    public Set<IdentityDocument> getIdentityDocuments() {
        return identityDocuments;
    }

    public void setIdentityDocuments(Set<IdentityDocument> identityDocuments) {
        this.identityDocuments = identityDocuments;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Set<PreviousJob> getPreviousJobs() {
        return previousJobs;
    }

    public void setPreviousJobs(Set<PreviousJob> previousJobs) {
        this.previousJobs = previousJobs;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("username", username)
                .add("email", email)
                .add("password", password)
                .add("lastName", lastName)
                .add("firstName", firstName)
                .add("gender", gender)
                .add("birthPlace", birthPlace)
                .add("personIdentifier", personIdentifier)
                .add("birthDate", birthDate)
                .add("birthName", birthName)
                .add("homePhoneNumber", homePhoneNumber)
                .add("mobilePhoneNumber", mobilePhoneNumber)
                .add("jobPosition", jobPosition)
                .add("entity", entity)
                .add("location", location)
                .add("department", department)
                .add("ITDeduction", ITDeduction)
                .add("joiningDate", joiningDate)
                .add("currentPositionStartingDate", currentPositionStartingDate)
                .add("nationality", nationality)
                .add("roles", roles)
                .add("academicStudies", academicStudies)
                .add("addresses", addresses)
                .add("bankAccount", bankAccounts)
                .add("children", children)
                .add("citizenships", citizenships)
                .add("holidays", holidays)
                .add("identityDocuments", identityDocuments)
                .add("languages", languages)
                .add("maritalStatus", maritalStatus)
                .add("partner", partner)
                .add("previousJobs", previousJobs)
                .toString();
    }
}
