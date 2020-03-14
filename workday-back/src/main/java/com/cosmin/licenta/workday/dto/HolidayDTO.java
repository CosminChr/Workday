package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.HolidayReferential;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class HolidayDTO {

    private Long id;

    private Employee employee;

    private ReferentialDTO holidayType;

    private LocalDate from;

    private LocalDate to;

    private boolean approved;

    private boolean validated;

    private String comment;

    private byte[] attestingDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ReferentialDTO getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(ReferentialDTO holidayType) {
        this.holidayType = holidayType;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
                .add("holidayType", holidayType)
                .add("from", from)
                .add("to", to)
                .add("approved", approved)
                .add("validated", validated)
                .add("comment", comment)
                .add("attestingDocument", attestingDocument)
                .toString();
    }
}
