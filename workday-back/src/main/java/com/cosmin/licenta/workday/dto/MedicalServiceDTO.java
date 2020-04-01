package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.MedicalServiceProviderReferential;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class MedicalServiceDTO {

    private Long id;

    private Employee employee;

    private MedicalServiceProviderReferential medicalServiceProvider;

    private LocalDate initiationDate;

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

    public MedicalServiceProviderReferential getMedicalServiceProvider() {
        return medicalServiceProvider;
    }

    public void setMedicalServiceProvider(MedicalServiceProviderReferential medicalServiceProvider) {
        this.medicalServiceProvider = medicalServiceProvider;
    }

    public LocalDate getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(LocalDate initiationDate) {
        this.initiationDate = initiationDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employee", employee)
                .add("medicalServiceProvider", medicalServiceProvider)
                .add("initiationDate", initiationDate)
                .toString();
    }
}
