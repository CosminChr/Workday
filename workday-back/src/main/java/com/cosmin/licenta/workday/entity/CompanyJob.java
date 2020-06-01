package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "workday_company_job")
public class CompanyJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title")
    private String jobTitle;

    @OneToOne
    @JoinColumn(name = "job_field_id")
    private JobFieldReferential jobField;

    @Column(name = "locality")
    private String locality;

    @Column(name = "country")
    private String country;

    @OneToOne
    @JoinColumn(name = "required_experience_id")
    private RequiredExperienceReferential requiredExperience;

    @OneToOne
    @JoinColumn(name = "contract_type_id")
    private ContractTypeReferential contractType;

    @Column(name = "posting_date")
    private LocalDate postingDate;

    @OneToMany(mappedBy = "companyJob")
    @JsonManagedReference
    private Set<JobApplication> jobApplications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobFieldReferential getJobField() {
        return jobField;
    }

    public void setJobField(JobFieldReferential jobField) {
        this.jobField = jobField;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RequiredExperienceReferential getRequiredExperience() {
        return requiredExperience;
    }

    public void setRequiredExperience(RequiredExperienceReferential requiredExperience) {
        this.requiredExperience = requiredExperience;
    }

    public ContractTypeReferential getContractType() {
        return contractType;
    }

    public void setContractType(ContractTypeReferential contractType) {
        this.contractType = contractType;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public Set<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(Set<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }
}
