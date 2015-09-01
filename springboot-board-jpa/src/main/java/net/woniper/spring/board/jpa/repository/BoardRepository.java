package net.woniper.spring.board.jpa.repository;

import net.woniper.spring.board.jpa.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woniper on 15. 9. 1..
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
}
