package com.example.springboot.repository;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ChequeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    private String number;
    private String digit;

    @AttributeOverrides({
            @AttributeOverride(name = "bankCode", column = @Column(name = "payee_bank_code")),
            @AttributeOverride(name = "branchCode", column = @Column(name = "payee_branch_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "payee_account_number"))
    })
    @Embedded
    private AccountEntity payee;

    @AttributeOverrides({
            @AttributeOverride(name = "bankCode", column = @Column(name = "drawer_bank_code")),
            @AttributeOverride(name = "branchCode", column = @Column(name = "drawer_branch_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "drawer_account_number"))
    })
    @Embedded
    private AccountEntity drawer;

    public ChequeEntity() {}

    public ChequeEntity(BigDecimal amount, String number, String digit, AccountEntity payee, AccountEntity drawer) {
        this.amount = amount;
        this.number = number;
        this.digit = digit;
        this.payee = payee;
        this.drawer = drawer;
    }

    public ChequeEntity(Long id, BigDecimal amount, String number, String digit, AccountEntity payee, AccountEntity drawer) {
        this.id = id;
        this.amount = amount;
        this.number = number;
        this.digit = digit;
        this.payee = payee;
        this.drawer = drawer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit;
    }

    public AccountEntity getPayee() {
        return payee;
    }

    public void setPayee(AccountEntity payee) {
        this.payee = payee;
    }

    public AccountEntity getDrawer() {
        return drawer;
    }

    public void setDrawer(AccountEntity drawer) {
        this.drawer = drawer;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", amount=" + amount +
                ", number='" + number + '\'' +
                ", digit='" + digit + '\'' +
                ", payee=" + payee +
                ", drawer=" + drawer +
                '}';
    }
}
