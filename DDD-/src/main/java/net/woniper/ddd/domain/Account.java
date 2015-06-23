package net.woniper.ddd.domain;

import net.woniper.ddd.domain.support.NotWithDrawException;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by woniper on 15. 6. 23..
 */
@Entity
public class Account extends AbstractPersistable<Long> {

//    @Column(unique = true)
    private String accountNumber;

    private double balance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Transient
    private List<Ledger> ledgers = new ArrayList<>();

    public Account() {}

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Ledger> getLedgers() {
        return ledgers;
    }

    // 장부 기록
    public boolean writeLedger(Ledger ledger) {
        return ledgers.add(ledger);
    }

    // 계좌 개설
    public boolean createAccount(Customer customer) {
        setCustomer(customer);
        return customer.addAccount(this);
    }

    // 입금
    public void deposit(double amount) {
        balance += amount;
    }

    // 출금
    public void withdraw(double amount) {
        if(balance >= amount)
            balance -= amount;
        else
            throw new NotWithDrawException(amount);
    }

}
