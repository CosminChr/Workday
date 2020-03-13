package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.HolidayReferential;

import java.time.LocalDate;

public class HolidayDTO {

    private Long id;

    private HolidayReferential holidayType;

    private LocalDate from;

    private LocalDate to;

    private String comment;

    private byte[] attestingDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HolidayReferential getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayReferential holidayType) {
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
}
