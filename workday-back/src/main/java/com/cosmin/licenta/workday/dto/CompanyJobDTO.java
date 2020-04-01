package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import com.cosmin.licenta.workday.entity.RequiredExperienceReferential;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class CompanyJobDTO {

    private Long id;

    private String jobTitle;

    private RequiredExperienceReferential requiredExperience;

    private ContractTypeReferential contractType;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("jobTitle", jobTitle)
                .add("requiredExperience", requiredExperience)
                .add("contractType", contractType)
                .add("postingDate", postingDate)
                .toString();
    }
}
