package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

public class CitizenshipDTO {

    private Long id;

    private ReferentialDTO citizenship;

    private byte[] attestingDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReferentialDTO getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(ReferentialDTO citizenship) {
        this.citizenship = citizenship;
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
                .add("citizenship", citizenship)
                .add("attestingDocument", attestingDocument)
                .toString();
    }
}
