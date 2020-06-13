package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "workday_work_from_home")
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

    @OneToOne
    @JoinColumn(name = "day_of_week_1_id")
    private DayOfWeekReferential dayOfWeekDay1;

    @OneToOne
    @JoinColumn(name = "day_of_week_2_id")
    private DayOfWeekReferential dayOfWeekDay2;

    @OneToOne
    @JoinColumn(name = "potential_day_of_week_1_id")
    private DayOfWeekReferential potentialDayOfWeekDay1;

    @OneToOne
    @JoinColumn(name = "potential_day_of_week_2_id")
    private DayOfWeekReferential potentialDayOfWeekDay2;

    @Column(name = "last_initiation_date")
    private LocalDateTime lastInitiationDate;

    @Column(name = "last_processing_date")
    private LocalDateTime lastProcessingDate;

    private boolean approved;

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

    public DayOfWeekReferential getPotentialDayOfWeekDay1() {
        return potentialDayOfWeekDay1;
    }

    public void setPotentialDayOfWeekDay1(DayOfWeekReferential potentialDayOfWeekDay1) {
        this.potentialDayOfWeekDay1 = potentialDayOfWeekDay1;
    }

    public DayOfWeekReferential getPotentialDayOfWeekDay2() {
        return potentialDayOfWeekDay2;
    }

    public void setPotentialDayOfWeekDay2(DayOfWeekReferential potentialDayOfWeekDay2) {
        this.potentialDayOfWeekDay2 = potentialDayOfWeekDay2;
    }

    public LocalDateTime getLastInitiationDate() {
        return lastInitiationDate;
    }

    public void setLastInitiationDate(LocalDateTime lastInitiationDate) {
        this.lastInitiationDate = lastInitiationDate;
    }

    public LocalDateTime getLastProcessingDate() {
        return lastProcessingDate;
    }

    public void setLastProcessingDate(LocalDateTime lastProcessingDate) {
        this.lastProcessingDate = lastProcessingDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
