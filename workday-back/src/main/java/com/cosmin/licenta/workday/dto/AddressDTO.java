package com.cosmin.licenta.workday.dto;

import com.google.common.base.MoreObjects;

public class AddressDTO {

    private Long id;

    private EmployeeDTO employee;

    private ReferentialDTO addressType;

    private String street;

    private String number;

    private String block;

    private String stairwell;

    private Integer floor;

    private Integer apartmentNumber;

    private LocalityReferentialDTO locality;

    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
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

    public LocalityReferentialDTO getLocality() {
        return locality;
    }

    public void setLocality(LocalityReferentialDTO locality) {
        this.locality = locality;
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
                .add("employee", employee)
                .add("addressType", addressType)
                .add("street", street)
                .add("number", number)
                .add("block", block)
                .add("stairwell", stairwell)
                .add("floor", floor)
                .add("apartmentNumber", apartmentNumber)
                .add("locality", locality)
                .add("postalCode", postalCode)
                .toString();
    }
}
