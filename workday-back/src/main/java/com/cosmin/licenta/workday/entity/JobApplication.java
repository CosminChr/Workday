package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "workday_job_application")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "company_job_id")
    private CompanyJob companyJob;

    @ManyToOne
    @JoinColumn(name = "submitted_by_id")
    private Employee submittedBy;

    @Lob
    @Column(name = "cv")
    private byte[] cv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyJob getCompanyJob() {
        return companyJob;
    }

    public void setCompanyJob(CompanyJob companyJob) {
        this.companyJob = companyJob;
    }

    public Employee getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Employee submittedBy) {
        this.submittedBy = submittedBy;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }
}
