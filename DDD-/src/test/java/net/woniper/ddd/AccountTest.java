package net.woniper.ddd;

import net.woniper.ddd.domain.Account;
import net.woniper.ddd.domain.Customer;
import net.woniper.ddd.domain.Ledger;
import net.woniper.ddd.domain.support.NotWithDrawException;
import net.woniper.ddd.domain.support.Type;
import net.woniper.ddd.repository.AccountRepository;
import net.woniper.ddd.service.AccountCreateService;
import net.woniper.ddd.service.FundsTransferService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by woniper on 15. 6. 23..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class AccountTest {

    @Autowired private AccountRepository accountRepository;

    @Autowired private AccountCreateService accountCreateService;
    @Autowired private FundsTransferService fundsTransferService;

    private Account a123;
    private Account b123;

    @Before
    public void setUp() throws Exception {
        Customer customer1 = new Customer("woniper", "seoul");
        Customer customer2 = new Customer("kyungWon", "pangpyung");

        a123 = new Account("a123");
        b123 = new Account("b123");

        a123.createAccount(customer1);
        b123.createAccount(customer2);

        accountCreateService.createAccount(a123);
        accountCreateService.createAccount(b123);
    }

    @Test
    public void test_입금_출금() throws Exception {
        assertEquals(a123.getBalance(), 0.0, 0);

        // 입금
        a123.deposit(100.0);
        assertEquals(a123.getBalance(), 100.0, 0);

        // 출금
        a123.withdraw(50.0);
        assertEquals(a123.getBalance(), 50.0, 0);
    }

    @Test(expected = NotWithDrawException.class)
    public void test_NotWithDrawException() throws Exception {
        assertEquals(a123.getBalance(), 0.0, 0);
        a123.withdraw(50.0);
    }

    @Test
    public void test_장부() throws Exception {
        double amount = 100.0 + 100.0 * 0.2;
        accountCreateService.deposit(a123.getAccountNumber(), amount, "입금 테스트");

        Account account = accountRepository.findByAccountNumber(a123.getAccountNumber());
        Ledger ledger = account.getLedgers().get(0);

        assertEquals(ledger.getType(), Type.DEPOSIT);
        assertEquals(ledger.getDescription(), "입금 테스트");
    }

    @Test
    public void test_계좌_이체() throws Exception {
        // given
        a123.deposit(200.0);
        String toAccountNumber = a123.getAccountNumber();
        String fromAccountNumber = b123.getAccountNumber();
        double amount = 100.0;

        // when
        boolean isTransfer = fundsTransferService.transfer(toAccountNumber, fromAccountNumber, amount);

        // then
        assertTrue(isTransfer);
    }

    @Test
    public void testAutowired() throws Exception {
        assertNotNull(accountCreateService);
        assertNotNull(fundsTransferService);
    }
}
