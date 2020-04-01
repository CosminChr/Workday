package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.Employee;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.time.LocalDate;

public class OvertimeDTO {

    private Long id;

    private Employee employee;

    private Integer numberOfHours;

    private LocalDate effectuationDate;

    private LocalDate initiationDate;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employee", employee)
                .add("numberOfHours", numberOfHours)
                .add("effectuationDate", effectuationDate)
                .add("initiationDate", initiationDate)
                .toString();
    }
}
