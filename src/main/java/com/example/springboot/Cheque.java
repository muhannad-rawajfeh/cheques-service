package com.example.springboot;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Cheque {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private BigDecimal amount;
    private String number;
    private String digit;

    @Embedded
    private Account payee;
    @Embedded
    private Account drawer;

    protected Cheque() {}

    public Cheque(BigDecimal amount, String number, String digit, Account payee, Account drawer) {
        this.amount = amount;
        this.number = number;
        this.digit = digit;
        this.payee = payee;
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
