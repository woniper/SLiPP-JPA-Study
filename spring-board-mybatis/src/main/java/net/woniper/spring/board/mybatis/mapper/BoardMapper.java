package net.woniper.spring.board.mybatis.mapper;

import net.woniper.spring.board.mybatis.entity.Board;
import net.woniper.spring.board.mybatis.support.Pageable;

import java.util.List;

/**
 * Created by woniper on 15. 9. 1..
 */
public interface BoardMapper {

    Board findBoard(Long id);

    List<Board> findBoards(Pageable pageable);

    int insertBoard(Board board);

    int updateBoard(Board board);

    int deleteBoard(Long id);

}
