package net.woniper.jpa.repository;

import net.woniper.jpa.domain.Order;
import net.woniper.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woniper on 15. 5. 18..
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByOrderNameAndUser(String orderName, User user);
}
