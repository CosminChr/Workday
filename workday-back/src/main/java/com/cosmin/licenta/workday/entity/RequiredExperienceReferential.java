package com.cosmin.licenta.workday.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_required_experience_ref")
public class RequiredExperienceReferential extends AbstractReferential implements Serializable {
}
