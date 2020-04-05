package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity
@Table(name = "workday_work_from_home")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class WorkFromHome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "start_date_day_1")
    private LocalDate startDateDay1;

    @Column(name = "start_date_day_2")
    private LocalDate startDateDay2;

    @JoinColumn(name = "day_of_week_1_id")
    private DayOfWeekReferential dayOfWeekDay1;

    @JoinColumn(name = "day_of_week_2_id")
    private DayOfWeekReferential dayOfWeekDay2;

    @Column(name = "last_initiation_date")
    private LocalDate lastInitiationDate;

    @Column(name = "last_approval_date")
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
}
