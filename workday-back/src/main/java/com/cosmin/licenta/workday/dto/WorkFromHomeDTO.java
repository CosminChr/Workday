package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.DayOfWeekReferential;
import com.cosmin.licenta.workday.entity.Employee;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.time.LocalDate;

public class WorkFromHomeDTO {

    private Long id;

    private Employee employee;

    private LocalDate startDateDay1;

    private LocalDate startDateDay2;

    private DayOfWeekReferential dayOfWeekDay1;

    private DayOfWeekReferential dayOfWeekDay2;

    private LocalDate lastInitiationDate;

    private LocalDate lastApprovalDate;

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

    public LocalDate getStartDateDay1() {
        return startDateDay1;
    }

    public void setStartDateDay1(LocalDate startDateDay1) {
        this.startDateDay1 = startDateDay1;
    }

    public LocalDate getStartDateDay2() {
        return startDateDay2;
    }

    public void setStartDateDay2(LocalDate startDateDay2) {
        this.startDateDay2 = startDateDay2;
    }

    public DayOfWeekReferential getDayOfWeekDay1() {
        return dayOfWeekDay1;
    }

    public void setDayOfWeekDay1(DayOfWeekReferential dayOfWeekDay1) {
        this.dayOfWeekDay1 = dayOfWeekDay1;
    }

    public DayOfWeekReferential getDayOfWeekDay2() {
        return dayOfWeekDay2;
    }

    public void setDayOfWeekDay2(DayOfWeekReferential dayOfWeekDay2) {
        this.dayOfWeekDay2 = dayOfWeekDay2;
    }

    public LocalDate getLastInitiationDate() {
        return lastInitiationDate;
    }

    public void setLastInitiationDate(LocalDate lastInitiationDate) {
        this.lastInitiationDate = lastInitiationDate;
    }

    public LocalDate getLastApprovalDate() {
        return lastApprovalDate;
    }

    public void setLastApprovalDate(LocalDate lastApprovalDate) {
        this.lastApprovalDate = lastApprovalDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employee", employee)
                .add("startDateDay1", startDateDay1)
                .add("startDateDay2", startDateDay2)
                .add("dayOfWeekDay1", dayOfWeekDay1)
                .add("dayOfWeekDay2", dayOfWeekDay2)
                .add("lastInitiationDate", lastInitiationDate)
                .add("lastApprovalDate", lastApprovalDate)
                .toString();
    }
}
