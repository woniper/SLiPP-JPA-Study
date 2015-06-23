package net.woniper.ddd.service;

import net.woniper.ddd.domain.Account;
import net.woniper.ddd.domain.Ledger;
import net.woniper.ddd.domain.support.Type;
import net.woniper.ddd.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by woniper on 15. 6. 23..
 */
@Service
public class FundsTransferService {

    @Autowired private AccountRepository accountRepository;

    @Transactional(rollbackFor = Exception.class)
    public boolean transfer(String toAccountNumber, String fromAccountNumber, double amount) {
        Account to = accountRepository.findByAccountNumber(toAccountNumber);
        Account from = accountRepository.findByAccountNumber(fromAccountNumber);
        if(to != null && from != null) {
            to.withdraw(amount);
            Ledger withdrawLedger = new Ledger(amount, Type.WITHDRAW, fromAccountNumber + "로 입금");
            to.writeLedger(withdrawLedger);
            accountRepository.saveAndFlush(to);

            from.deposit(amount);
            Ledger depositLedger = new Ledger(amount, Type.DEPOSIT, toAccountNumber + "에서 입금");
            from.writeLedger(depositLedger);
            accountRepository.saveAndFlush(from);

            return true;
        }
        return false;
    }
}
