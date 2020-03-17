package com.cosmin.licenta.workday.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workday_identity_document")
public class IdentityDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private IdentityDocumentTypeReferential identityDocumentType;

    @Column(name = "series_and_number")
    private String seriesAndNumber;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    private String issuer;

    private CountryReferential country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IdentityDocumentTypeReferential getIdentityDocumentType() {
        return identityDocumentType;
    }

    public void setIdentityDocumentType(IdentityDocumentTypeReferential identityDocumentType) {
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

    public CountryReferential getCountry() {
        return country;
    }

    public void setCountry(CountryReferential country) {
        this.country = country;
    }
}
