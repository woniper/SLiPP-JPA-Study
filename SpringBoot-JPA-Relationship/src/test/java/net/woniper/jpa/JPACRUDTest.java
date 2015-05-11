package net.woniper.jpa;

import net.woniper.jpa.config.JPAConfig;
import net.woniper.jpa.domain.otmAndmto.Order;
import net.woniper.jpa.domain.otmAndmto.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringBootJpaApplication.class, JPAConfig.class})
@WebAppConfiguration
public class JPACRUDTest {

	@PersistenceUnit
	private EntityManagerFactory persistenceEntityManager;

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private User user;

	@Test
	public void configTest() {
		assertNotNull(persistenceEntityManager);
		assertNotNull(entityManagerFactory);
	}

	@Test
	public void update() {
		// update
		User updateUser = entityManager.find(User.class, user.getUserId());
		updateUser.setNickName("update nickName");
		updateUser.setAddress("update address");

		entityManager.merge(updateUser);

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
	}

}
