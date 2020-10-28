package com.example.springboot;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Cheque {

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
    private Account payee;

    @AttributeOverrides({
            @AttributeOverride(name = "bankCode", column = @Column(name = "drawer_bank_code")),
            @AttributeOverride(name = "branchCode", column = @Column(name = "drawer_branch_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "drawer_account_number"))
    })
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
