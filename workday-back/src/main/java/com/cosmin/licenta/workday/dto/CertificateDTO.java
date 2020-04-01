package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import com.cosmin.licenta.workday.entity.Employee;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class CertificateDTO {

    private Long id;

    private Employee employee;

    private CertificateTypeReferential certificateType;

    private LocalDate generationDate;

    private byte[] certificate;

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

    public CertificateTypeReferential getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateTypeReferential certificateType) {
        this.certificateType = certificateType;
    }

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employee", employee)
                .add("certificateType", certificateType)
                .add("generationDate", generationDate)
                .add("certificate", certificate)
                .toString();
    }
}
