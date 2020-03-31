package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "workday_citizenship")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Citizenship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "citizenship_id")
    private CitizenshipReferential citizenship;

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

    public byte[] getAttestingDocument() {
        return attestingDocument;
    }

    public void setAttestingDocument(byte[] attestingDocument) {
        this.attestingDocument = attestingDocument;
    }
}
