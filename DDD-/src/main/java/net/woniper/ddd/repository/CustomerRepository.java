package net.woniper.ddd.repository;

import net.woniper.ddd.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woniper on 15. 6. 23..
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
