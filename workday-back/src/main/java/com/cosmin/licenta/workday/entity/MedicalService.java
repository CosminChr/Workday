package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workday_medical_service")
public class MedicalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "medical_service_provider_id")
    private MedicalServiceProviderReferential medicalServiceProvider;

    @Column(name = "initiation_date")
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
}
