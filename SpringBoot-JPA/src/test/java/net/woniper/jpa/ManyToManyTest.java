package net.woniper.jpa;

import net.woniper.jpa.config.JPAConfig;
import net.woniper.jpa.domain.mtm.Category;
import net.woniper.jpa.domain.mtm.Product;
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
public class ManyToManyTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Test
	public void oneToOneTest() {

		Category category = new Category();
		category.setName("IT");
		entityManager.persist(category);

		Product product = new Product();
		product.setName("MacBook");
		product.setPrice(1000);

		product.addCategory(category);
		category.addProduct(product);
		entityManager.persist(product);

		Assert.assertEquals(product.getProductId(), category.getProducts().get(0).getProductId());
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
