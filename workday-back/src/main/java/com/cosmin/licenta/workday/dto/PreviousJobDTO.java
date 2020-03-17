package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.City;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class PreviousJobDTO {

    private Long id;

    private String employer;

    private String position;

    private City city;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String fiscalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employer", employer)
                .add("position", position)
                .add("city", city)
                .add("fromDate", fromDate)
                .add("toDate", toDate)
                .add("fiscalCode", fiscalCode)
                .toString();
    }
}
