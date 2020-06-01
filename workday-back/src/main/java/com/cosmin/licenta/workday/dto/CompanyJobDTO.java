package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import com.cosmin.licenta.workday.entity.RequiredExperienceReferential;
import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.StringJoiner;

public class CompanyJobDTO {

    private Long id;

    private String jobTitle;

    private ReferentialDTO jobField;

    private String locality;

    private String country;

    private ReferentialDTO requiredExperience;

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

    public ReferentialDTO getJobField() {
        return jobField;
    }

    public void setJobField(ReferentialDTO jobField) {
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

    public ReferentialDTO getRequiredExperience() {
        return requiredExperience;
    }

    public void setRequiredExperience(ReferentialDTO requiredExperience) {
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
                .add("jobField", jobField)
                .add("locality", locality)
                .add("country", country)
                .add("requiredExperience", requiredExperience)
                .add("contractType", contractType)
                .add("postingDate", postingDate)
                .toString();
    }
}
