package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

public class LocalityReferentialDTO extends ReferentialDTO {

    private ReferentialDTO countyReferential;

    private ReferentialDTO countryReferential;

    public ReferentialDTO getCountyReferential() {
        return countyReferential;
    }

    public ReferentialDTO getCountryReferential() {
        return countryReferential;
    }

    public void setCountryReferential(ReferentialDTO countryReferential) {
        this.countryReferential = countryReferential;
    }

    public void setCountyReferential(ReferentialDTO countyReferential) {
        this.countyReferential = countyReferential;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("label", label)
                .add("countyReferential", countyReferential)
                .add("countryReferential", countryReferential)
                .toString();
    }
}
