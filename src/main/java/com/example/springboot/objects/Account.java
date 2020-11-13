package com.example.springboot.objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Account {

    @NotEmpty(message = "Bank code should not be empty")
    @Size(min = 2, max = 2, message = "Bank code must be 2 digits")
    private String bankCode;
    @NotEmpty(message = "Branch code should not be empty")
    @Size(min = 4, max = 4, message = "Branch code must be 4 digits")
    private String branchCode;
    @NotEmpty(message = "Account number should not be empty")
    @Size(min = 8, max = 8, message = "Account number must be 8 digits")
    private String accountNumber;

    public Account(String bankCode, String branchCode, String accountNumber) {
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
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
