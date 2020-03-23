package com.cosmin.licenta.workday.entity;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_locality_ref")
public class LocalityReferential extends AbstractReferential implements Serializable {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_id")
    private CountyReferential county;

    @OneToOne(fetch = FetchType.LAZY)
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

