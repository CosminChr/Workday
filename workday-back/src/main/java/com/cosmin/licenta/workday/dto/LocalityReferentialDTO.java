package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

public class LocalityReferentialDTO extends ReferentialDTO {

    private ReferentialDTO county;

    private ReferentialDTO country;

    public ReferentialDTO getCounty() {
        return county;
    }

    public void setCounty(ReferentialDTO countyl) {
        this.county = countyl;
    }

    public ReferentialDTO getCountry() {
        return country;
    }

    public void setCountry(ReferentialDTO country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("label", getLabel())
                .add("county", county)
                .add("country", country)
                .toString();
    }
}
