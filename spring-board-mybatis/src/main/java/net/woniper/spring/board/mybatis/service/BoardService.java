package net.woniper.spring.board.mybatis.service;

import net.woniper.spring.board.mybatis.entity.Board;
import net.woniper.spring.board.mybatis.support.BoardDto;
import net.woniper.spring.board.mybatis.support.Pageable;

import java.util.List;

/**
 * Created by woniper on 15. 9. 1..
 */
public interface BoardService {
    Board createBoard(BoardDto.Request boardDto);

    Board updateBoard(Long id, BoardDto.Request boardDto);

    boolean deleteBoard(Long id);

    Board getBoard(Long id);

    List<Board> getBoard(Pageable pageable);
}
