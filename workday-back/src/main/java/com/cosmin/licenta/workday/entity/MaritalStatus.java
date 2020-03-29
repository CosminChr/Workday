package com.cosmin.licenta.workday.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workday_marital_status")
public class MaritalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "marital_status_id")
    private MaritalStatusReferential maritalStatus;

    @Column(name = "starting_date")
    private LocalDate startingDate;

    @OneToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

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
}
