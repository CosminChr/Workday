package com.cosmin.licenta.workday.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workday_overtime")
public class Overtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JoinColumn(name = "number_of_hours")
    private Integer numberOfHours;

    @Column(name = "effectuation_date")
    private LocalDate effectuationDate;

    @Column(name = "initiation_date")
    private LocalDate initiationDate;

    private boolean approved;

    private boolean validated;

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

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public LocalDate getEffectuationDate() {
        return effectuationDate;
    }

    public void setEffectuationDate(LocalDate effectuationDate) {
        this.effectuationDate = effectuationDate;
    }

    public LocalDate getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(LocalDate initiationDate) {
        this.initiationDate = initiationDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
