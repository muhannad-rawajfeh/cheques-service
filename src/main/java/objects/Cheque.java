package objects;

import java.math.BigDecimal;

public class Cheque {

    private BigDecimal amount;
    private String number;
    private String digit;
    private Account payee;
    private Account drawer;

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
