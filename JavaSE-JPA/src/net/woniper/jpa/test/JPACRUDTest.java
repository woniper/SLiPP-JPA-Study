package net.woniper.jpa.test;

import net.woniper.jpa.domain.Order;
import net.woniper.jpa.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by woniper on 15. 4. 30..
 */
public class JPACRUDTest {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    private User user;

    @Test
    public void update() {
        // update
        User updateUser = entityManager.find(User.class, user.getUserId());
        updateUser.setNickName("update nickName");
        updateUser.setAddress("update address");

        // persistence Context Test
        assertEquals("update nickName", user.getNickName());
        assertEquals("update address", user.getAddress());

        // update Tests
        assertEquals("update nickName", updateUser.getNickName());
        assertEquals("update address", updateUser.getAddress());
    }

    @Test
    public void delete() {
        User getUser = entityManager.find(User.class, user.getUserId());
        entityManager.remove(getUser);
        User deleteUser = entityManager.find(User.class, user.getUserId());
        assertNull(deleteUser);
    }

    @Test
    public void select() {
        User findUser = entityManager.find(User.class, user.getUserId());
        assertEquals(user.getUserId(), findUser.getUserId());
        assertEquals(user.getUsername(), findUser.getUsername());
        assertEquals(user.getNickName(), findUser.getNickName());
        assertEquals(user.getAddress(), findUser.getAddress());
        assertEquals(user.totalPrice(), 145);

        // order
        assertEquals(user.getOrders().size(), 10);
    }

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // fixture
        user = new User();
        user.setUsername("kyungwon");
        user.setNickName("woniper");
        user.setAddress("seoul");
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order order = new Order("order" + i, "note" + i, i + 10,  user);
            entityManager.persist(order);
            orders.add(order);
        }
        user.setOrders(orders);
        entityManager.persist(user);
        System.out.println("============ fixture ===========\n" + user);
    }

    @After
    public void after() {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
