package com.cosmin.licenta.workday.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_language_ref")
public class LanguageReferential extends AbstractReferential implements Serializable {
}
