package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

public class AddressDTO {

    private Long id;

    private ReferentialDTO addressType;

    private String street;

    private String number;

    private String block;

    private String stairwell;

    private Integer floor;

    private Integer apartmentNumber;

    private LocalityReferentialDTO city;

    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReferentialDTO getAddressType() {
        return addressType;
    }

    public void setAddressType(ReferentialDTO addressType) {
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

    public LocalityReferentialDTO getCity() {
        return city;
    }

    public void setCity(LocalityReferentialDTO city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("addressType", addressType)
                .add("street", street)
                .add("number", number)
                .add("block", block)
                .add("stairwell", stairwell)
                .add("floor", floor)
                .add("apartmentNumber", apartmentNumber)
                .add("city", city)
                .add("postalCode", postalCode)
                .toString();
    }
}
