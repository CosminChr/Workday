package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workday_employee",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Employee extends User implements Serializable {

    @Size(max = 40)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 40)
    @Column(name = "first_name")
    private String firstName;

    @OneToOne
    @JoinColumn(name = "gender_id")
    private GenderReferential gender;

    @Size(max = 40)
    @Column(name = "birth_place")
    private String birthPlace;

    @Size(max = 13)
    @Column(name = "person_identifier")
    private String personIdentifier;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Size(max = 40)
    @Column(name = "birth_name")
    private String birthName;

    @Size(max = 10)
    @Column(name = "home_phone_number")
    private String homePhoneNumber;

    @Size(max = 10)
    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @OneToOne
    @JoinColumn(name = "job_position_id")
    private JobPositionReferential jobPosition;

    @Size(max = 30)
    private String entity;

    @Size(max = 30)
    private String location;

    @OneToOne
    @JoinColumn(name = "department_id")
    private DepartmentReferential department;

    @Column(name = "it_deduction")
    private Boolean ITDeduction;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "current_position_starting_date")
    private LocalDate currentPositionStartingDate;

    @OneToOne
    @JoinColumn(name = "nationality_id")
    private NationalityReferential nationality;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "workday_employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleReferential> roles = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<AcademicStudy> academicStudies;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<Address> addresses;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<Child> children;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<Citizenship> citizenships;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<Holiday> holidays;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<IdentityDocument> identityDocuments;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<Language> languages;

    @OneToOne(mappedBy = "employee")
    private MaritalStatus maritalStatus;

    @OneToOne(mappedBy = "employee")
    private Partner partner;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Set<PreviousJob> previousJobs;

    public Employee() {
    }

    public Employee(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
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

    public GenderReferential getGender() {
        return gender;
    }

    public void setGender(GenderReferential gender) {
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

    public JobPositionReferential getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPositionReferential jobPosition) {
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

    public DepartmentReferential getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentReferential department) {
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

    public NationalityReferential getNationality() {
        return nationality;
    }

    public void setNationality(NationalityReferential nationality) {
        this.nationality = nationality;
    }

    @Override
    public Set<RoleReferential> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleReferential> roles) {
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
}
