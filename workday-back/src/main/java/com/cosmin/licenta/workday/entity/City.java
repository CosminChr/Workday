package com.cosmin.licenta.workday.entity;

import javax.persistence.*;

@Entity
@Table(name = "workday_city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String county;

    private CountryReferential country;

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

    public CountryReferential getCountry() {
        return country;
    }

    public void setCountry(CountryReferential country) {
        this.country = country;
    }
}
