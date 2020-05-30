package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

public class JobApplicationDTO {

    private Long id;

    private CompanyJobDTO companyJob;

    private EmployeeDTO submittedBy;

    private byte[] cv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyJobDTO getCompanyJob() {
        return companyJob;
    }

    public void setCompanyJob(CompanyJobDTO companyJob) {
        this.companyJob = companyJob;
    }

    public EmployeeDTO getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(EmployeeDTO submittedBy) {
        this.submittedBy = submittedBy;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("companyJob", companyJob)
                .add("submittedBy", submittedBy)
                .add("cv", cv)
                .toString();
    }
}
