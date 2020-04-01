package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workday_company_job")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CompanyJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "required_experience_id")
    private RequiredExperienceReferential requiredExperience;

    @Column(name = "contract_type_id")
    private ContractTypeReferential contractType;

    @Column(name = "posting_date")
    private LocalDate postingDate;

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
}
