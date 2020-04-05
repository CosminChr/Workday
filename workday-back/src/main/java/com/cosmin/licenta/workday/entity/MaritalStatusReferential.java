package com.cosmin.licenta.workday.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_marital_status_ref")
public class MaritalStatusReferential extends AbstractReferential implements Serializable {
}
