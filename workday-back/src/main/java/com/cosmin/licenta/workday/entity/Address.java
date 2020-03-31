package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "workday_address")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "address_type_id")
    private AddressTypeReferential addressType;

    private String street;

    private String number;

    private String block;

    private String stairwell;

    private Integer floor;

    @Column(name = "apartment_number")
    private Integer apartmentNumber;

    @OneToOne
    @JoinColumn(name = "locality_id")
    private LocalityReferential locality;

    @Column(name = "postal_code")
    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public AddressTypeReferential getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressTypeReferential addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getStairwell() {
        return stairwell;
    }

    public void setStairwell(String stairwell) {
        this.stairwell = stairwell;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public LocalityReferential getLocality() {
        return locality;
    }

    public void setLocality(LocalityReferential locality) {
        this.locality = locality;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
