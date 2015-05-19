package net.woniper.jpa;

import net.woniper.jpa.domain.Order;
import net.woniper.jpa.domain.User;
import net.woniper.jpa.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by woniper on 15. 5. 18..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDataJpaApplication.class)
public class CrudTest {

    @Autowired private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername("lkw1989");
        user.setNickName("woniper");
        user.setAddress("seoul");
        user.addOrder(new Order("order1", "test1", 100, user));
        user.addOrder(new Order("order2", "test2", 100, user));
        user.setCreateDate(new Date());
        userRepository.save(user);
        userRepository.flush();
    }

    @Test
    public void testFind() throws Exception {
        User findUser = userRepository.findOne(user.getId());
        assertEquals(user.getId(), findUser.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        user.setAddress("update address");
        userRepository.saveAndFlush(user);

        User updateUser = userRepository.findOne(user.getId());
        assertEquals(user.getAddress(), updateUser.getAddress());
    }

    @Test
    public void testDelete() throws Exception {
        userRepository.delete(user);
        userRepository.flush();

        User deleteUser = userRepository.findOne(user.getId());
        assertNull(deleteUser);
    }

    @Test
    public void testContexts() throws Exception {
        assertNotNull(userRepository);
    }
}
