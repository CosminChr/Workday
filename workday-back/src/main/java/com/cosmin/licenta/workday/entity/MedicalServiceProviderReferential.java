package com.cosmin.licenta.workday.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_medical_service_provider_ref")
public class MedicalServiceProviderReferential extends AbstractReferential implements Serializable {
}

