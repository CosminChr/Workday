package com.cosmin.licenta.workday.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import static com.google.common.base.Objects.*;

@MappedSuperclass
public class AbstractReferential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractReferential that = (AbstractReferential) o;
        return equal(id, that.id) &&
                equal(label, that.label);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id, label);
    }
}
