package com.cosmin.licenta.workday.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workday_bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agency;

    private String IBAN;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private CurrencyReferential currency;

    @Column(name = "primary_account")
    private boolean primaryAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
