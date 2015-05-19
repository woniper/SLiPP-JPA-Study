package net.woniper.jpa.repository;

import net.woniper.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woniper on 15. 5. 2..
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}