package com.cosmin.licenta.workday.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_identity_document_type_ref")
public class IdentityDocumentTypeReferential extends AbstractReferential implements Serializable {
}
