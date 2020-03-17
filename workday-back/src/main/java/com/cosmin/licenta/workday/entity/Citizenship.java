package com.cosmin.licenta.workday.entity;

import javax.persistence.*;

@Entity
@Table(name = "workday_citizenship")
public class Citizenship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CitizenshipReferential citizenship;

    private NationalityReferential nationality;

    private byte [] attestingDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CitizenshipReferential getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(CitizenshipReferential citizenship) {
        this.citizenship = citizenship;
    }

    public NationalityReferential getNationality() {
        return nationality;
    }

    public void setNationality(NationalityReferential nationality) {
        this.nationality = nationality;
    }

    public byte[] getAttestingDocument() {
        return attestingDocument;
    }

    public void setAttestingDocument(byte[] attestingDocument) {
        this.attestingDocument = attestingDocument;
    }
}
