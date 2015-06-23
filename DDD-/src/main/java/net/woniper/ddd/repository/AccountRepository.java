package net.woniper.ddd.repository;

import net.woniper.ddd.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woniper on 15. 6. 23..
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);
}
