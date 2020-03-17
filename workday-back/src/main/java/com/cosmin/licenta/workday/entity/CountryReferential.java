package com.cosmin.licenta.workday.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "country_ref")
public class CountryReferential extends AbstractReferential implements Serializable {
}
