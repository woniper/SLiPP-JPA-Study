package net.woniper.jpa;

import net.woniper.jpa.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDataJpaApplication.class)
public class SpringBootDataJpaApplicationTests {

	@Autowired private UserRepository userRepository;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(userRepository);
	}

}
