package net.woniper.jpa;

import net.woniper.jpa.config.JPAConfig;
import net.woniper.jpa.domain.oto.Cellular;
import net.woniper.jpa.domain.oto.Person;
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
public class OneToOneTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Test
	public void oneToOneTest() {
		Cellular cellular = new Cellular();
		cellular.setNumber(521);
		entityManager.persist(cellular);

		Person person = new Person();
		person.setName("woniper");
		person.setCellular(cellular);
		entityManager.persist(person);

		Assert.assertEquals(person.getCellular().getId(), cellular.getId());
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
