package com.example.springboot;

import javax.persistence.Embeddable;

@Embeddable
public class Account {

    private String bankCode;
    private String branchCode;
    private String accountNumber;

    protected Account() {}

    public Account(String bankCode, String branchCode, String accountNumber) {
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "bankCode='" + bankCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
