package net.woniper.jpa;

import net.woniper.jpa.config.JPAConfig;
import net.woniper.jpa.domain.otmAndmto.Order;
import net.woniper.jpa.domain.otmAndmto.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringBootJpaApplication.class, JPAConfig.class})
@WebAppConfiguration
public class OneToManyAndManyToOneTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Test
	public void oneToOneTest() {
		Order order = new Order();
		order.setOrderName("test order");
		order.setPrice(123);
		order.setNote("test note");
		User user = new User();
		user.setUsername("lkw1989");
		user.setNickName("woniper");
		user.setAddress("seoul");

		// relationship
		user.addOrder(order);
		order.setUser(user);
		entityManager.persist(user);

		Assert.assertEquals(user.getOrders().get(0).getOrderId(), order.getOrderId());
	}

	@Before
	public void setUp() throws Exception {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@After
	public void after() {
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
