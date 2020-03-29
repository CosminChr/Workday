package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class PreviousJobDTO {

    private Long id;

    private String employer;

    private ReferentialDTO jobDomain;

    private String position;

    private LocalityReferentialDTO locality;

    private LocalDate fromDate;

    private LocalDate toDate;

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

    public ReferentialDTO getJobDomain() {
        return jobDomain;
    }

    public void setJobDomain(ReferentialDTO jobDomain) {
        this.jobDomain = jobDomain;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalityReferentialDTO getLocality() {
        return locality;
    }

    public void setLocality(LocalityReferentialDTO locality) {
        this.locality = locality;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employer", employer)
                .add("jobDomain", jobDomain)
                .add("position", position)
                .add("city", locality)
                .add("fromDate", fromDate)
                .add("toDate", toDate)
                .toString();
    }
}
