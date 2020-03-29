package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import com.cosmin.licenta.workday.entity.Partner;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class MaritalStatusDTO {

    private Long id;

    private EmployeeDTO employee;

    private ReferentialDTO maritalStatus;

    private LocalDate startingDate;

    private PartnerDTO partner;

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

    public ReferentialDTO getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(ReferentialDTO maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public PartnerDTO getPartner() {
        return partner;
    }

    public void setPartner(PartnerDTO partner) {
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
                .add("employee", employee)
                .add("maritalStatus", maritalStatus)
                .add("startingDate", startingDate)
                .add("partner", partner)
                .add("attestingDocument", attestingDocument)
                .toString();
    }
}
