package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDate;

public class IdentityDocumentDTO {

    private Long id;

    private EmployeeDTO employee;

    private ReferentialDTO identityDocumentType;

    private String seriesAndNumber;

    private LocalDate issueDate;

    private LocalDate expirationDate;

    private String issuer;

    private ReferentialDTO country;

    private String description;

    private byte [] attestingDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public ReferentialDTO getIdentityDocumentType() {
        return identityDocumentType;
    }

    public void setIdentityDocumentType(ReferentialDTO identityDocumentType) {
        this.identityDocumentType = identityDocumentType;
    }

    public String getSeriesAndNumber() {
        return seriesAndNumber;
    }

    public void setSeriesAndNumber(String seriesAndNumber) {
        this.seriesAndNumber = seriesAndNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public ReferentialDTO getCountry() {
        return country;
    }

    public void setCountry(ReferentialDTO country) {
        this.country = country;
    }

    public byte[] getAttestingDocument() {
        return attestingDocument;
    }

    public void setAttestingDocument(byte[] attestingDocument) {
        this.attestingDocument = attestingDocument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employee", employee)
                .add("identityDocumentType", identityDocumentType)
                .add("seriesAndNumber", seriesAndNumber)
                .add("issueDate", issueDate)
                .add("expirationDate", expirationDate)
                .add("issuer", issuer)
                .add("country", country)
                .add("description", description)
                .add("attestingDocument", attestingDocument)
                .toString();
    }
}
