package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

public class CityDTO {

    private Long id;

    private String name;

    private String county;

    private ReferentialDTO country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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
                .add("id", id)
                .add("name", name)
                .add("county", county)
                .add("country", country)
                .toString();
    }
}
