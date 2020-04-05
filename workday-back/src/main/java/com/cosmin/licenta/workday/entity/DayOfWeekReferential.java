package com.cosmin.licenta.workday.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_day_of_week_ref")
public class DayOfWeekReferential extends AbstractReferential implements Serializable {
}
