package com.cosmin.licenta.workday.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workday_employee",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Employee extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    private String username;

    @Size(max = 30)
    @Email
    private String email;

    @Size(max = 60)
    private String password;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "workday_employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleReferential> roles = new HashSet<>();

    public Employee() {
    }

    public Employee(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
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

    public Boolean isITDeduction() {
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

    @Override
    public Set<RoleReferential> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleReferential> roles) {
        this.roles = roles;
    }
}
