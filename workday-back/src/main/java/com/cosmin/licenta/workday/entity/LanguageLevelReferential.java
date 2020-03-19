package com.cosmin.licenta.workday.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_language_level_ref")
public class LanguageLevelReferential extends AbstractReferential implements Serializable {
}
