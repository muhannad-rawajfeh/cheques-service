package controller;

import javax.persistence.Embeddable;

@Embeddable
public class AccountEntity {

    private String bankCode;
    private String branchCode;
    private String accountNumber;

    protected AccountEntity() {}

    public AccountEntity(String bankCode, String branchCode, String accountNumber) {
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
