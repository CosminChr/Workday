package com.cosmin.licenta.workday.entity;

import javax.persistence.*;

@Entity
@Table(name = "workday_citizenship")
public class Citizenship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id")
    private CitizenshipReferential citizenship;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_id")
    private NationalityReferential nationality;

    @Column(name = "attesting_document")
    private byte [] attestingDocument;

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
