package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.CurrencyReferential;
import com.google.common.base.MoreObjects;

import java.time.LocalDate;

public class BankAccountDTO {

    private Long id;

    private EmployeeDTO employee;

    private String bank;

    private String agency;

    private String IBAN;

    private LocalDate expirationDate;

    private CurrencyReferential currency;

    private boolean primaryAccount;

    private byte[] attestingDocument;

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CurrencyReferential getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyReferential currency) {
        this.currency = currency;
    }

    public boolean isPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(boolean primaryAccount) {
        this.primaryAccount = primaryAccount;
    }

    public byte[] getAttestingDocument() {
        return attestingDocument;
    }

    public void setAttestingDocument(byte[] attestingDocument) {
        this.attestingDocument = attestingDocument;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("employee", employee)
                .add("bank", bank)
                .add("agency", agency)
                .add("IBAN", IBAN)
                .add("expirationDate", expirationDate)
                .add("currency", currency)
                .add("primaryAccount", primaryAccount)
                .add("attestingDocument", attestingDocument)
                .toString();
    }
}
