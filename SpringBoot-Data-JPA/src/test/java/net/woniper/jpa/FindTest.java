package net.woniper.jpa;

import net.woniper.jpa.domain.Order;
import net.woniper.jpa.domain.User;
import net.woniper.jpa.repository.OrderRepository;
import net.woniper.jpa.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by woniper on 15. 5. 18..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDataJpaApplication.class)
public class FindTest {

    @Autowired private UserRepository userRepository;
    @Autowired private OrderRepository orderRepository;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername("lkw1989");
        user.setNickName("woniper");
        user.setAddress("seoul");
        for (int i = 0; i < 10; i++) {
            user.addOrder(new Order("order" + i, "test" + i, 100, user));
        }
        user.setCreateDate(new Date());
        userRepository.save(user);
        userRepository.flush();
    }

    @Test
    public void testFindAnd() throws Exception {
        Order o = getOrder();
        Order order = orderRepository.findByOrderNameAndUser(o.getOrderName(), user);
        assertEquals(o.getId(), order.getId());
    }

    @Test
    public void testFindPaging() throws Exception {
        Pageable pageable = new PageRequest(0, 5);
        Page<Order> orders = orderRepository.findAll(pageable);
        assertEquals(5, orders.getSize());
    }

    private Order getOrder() {
        return user.getOrders().get(0);
    }

    @Test
    public void testContexts() throws Exception {
        assertNotNull(userRepository);
    }
}
