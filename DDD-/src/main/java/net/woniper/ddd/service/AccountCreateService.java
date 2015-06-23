package net.woniper.ddd.service;

import net.woniper.ddd.domain.Account;
import net.woniper.ddd.domain.Ledger;
import net.woniper.ddd.domain.support.Type;
import net.woniper.ddd.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by woniper on 15. 6. 23..
 */
@Service
public class AccountCreateService {

    @Autowired private AccountRepository repository;
//    @Autowired private LedgerRepository ledgerRepository;

    public Account createAccount(Account account) {
        return repository.saveAndFlush(account);
    }

    public Account deposit(String accountNumber, double amount, String description) {
        return depositAndWithdraw(accountNumber, amount, description, Type.DEPOSIT);
    }

    public Account withdraw(String accountNumber, double amount, String description) {
        return depositAndWithdraw(accountNumber, amount, description, Type.WITHDRAW);
    }

    private Account depositAndWithdraw(String accountNumber, double amount, String description, Type type) {
        Account account = repository.findByAccountNumber(accountNumber);
        if(account != null) {
            account.deposit(amount);
            Ledger ledger = new Ledger(amount, type, description);
            account.writeLedger(ledger);
        }
        return account;
    }

}
