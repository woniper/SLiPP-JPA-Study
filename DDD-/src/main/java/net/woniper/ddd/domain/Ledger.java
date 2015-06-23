package net.woniper.ddd.domain;

import net.woniper.ddd.domain.support.Type;

/**
 * Created by woniper on 15. 6. 23..
 */
public class Ledger {

    private double balance;

    private Type type;

    private String description;

    public Ledger() {
    }

    public Ledger(double balance, Type type, String description) {
        this.balance = balance;
        this.type = type;
        this.description = description;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Ledger{" +
                ", balance=" + balance +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}