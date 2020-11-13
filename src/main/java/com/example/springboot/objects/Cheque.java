package com.example.springboot.objects;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Cheque {

    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0", inclusive = false, message = "Amount should not be negative or zero")
    @DecimalMax(value = "1000000", message = "Amount should not be more than 1000000")
    private BigDecimal amount;
    @NotEmpty(message = "Cheque number should not be empty")
    private String number;
    private String digit;
    @Valid
    private Account payee;
    @Valid
    private Account drawer;

    public Cheque() {
    }

    public Cheque(BigDecimal amount, String number, String digit, Account payee, Account drawer) {
        this.amount = amount;
        this.number = number;
        this.digit = digit;
        this.payee = payee;
        this.drawer = drawer;
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

    public Account getPayee() {
        return payee;
    }

    public void setPayee(Account payee) {
        this.payee = payee;
    }

    public Account getDrawer() {
        return drawer;
    }

    public void setDrawer(Account drawer) {
        this.drawer = drawer;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "amount=" + amount +
                ", number='" + number + '\'' +
                ", digit='" + digit + '\'' +
                ", payee=" + payee +
                ", drawer=" + drawer +
                '}';
    }
}
