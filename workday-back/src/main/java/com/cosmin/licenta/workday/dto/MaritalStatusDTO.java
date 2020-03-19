package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import com.cosmin.licenta.workday.entity.Partner;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class MaritalStatusDTO {

    private Long id;

    private MaritalStatusReferential maritalStatus;

    private LocalDate startingDate;

    private Partner partner;

    private byte [] attestingDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaritalStatusReferential getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatusReferential maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public byte[] getAttestingDocument() {
        return attestingDocument;
    }

    public void setAttestingDocument(byte[] attestingDocument) {
        this.attestingDocument = attestingDocument;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("maritalStatus", maritalStatus)
                .add("startingDate", startingDate)
                .add("partner", partner)
                .add("attestingDocument", attestingDocument)
                .toString();
    }
}
