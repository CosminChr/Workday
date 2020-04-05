package com.cosmin.licenta.workday.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_locality_ref")
public class LocalityReferential extends AbstractReferential implements Serializable {

    @OneToOne
    @JoinColumn(name = "county_id")
    private CountyReferential county;

    @OneToOne
    @JoinColumn(name = "country_id")
    private CountryReferential country;

    public CountyReferential getCounty() {
        return county;
    }

    public void setCounty(CountyReferential county) {
        this.county = county;
    }

    public CountryReferential getCountry() {
        return country;
    }

    public void setCountry(CountryReferential country) {
        this.country = country;
    }
}

