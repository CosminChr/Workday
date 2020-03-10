package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReferentialDTO {

    @NotBlank
    Long id;

    @NotBlank
    @Size(max = 100)
    String label;

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
        ReferentialDTO that = (ReferentialDTO) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, label);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("label", label)
                .toString();
    }
}
