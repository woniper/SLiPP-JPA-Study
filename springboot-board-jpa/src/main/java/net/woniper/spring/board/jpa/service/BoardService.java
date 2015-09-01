package net.woniper.spring.board.jpa.service;

import net.woniper.spring.board.jpa.entity.Board;
import net.woniper.spring.board.jpa.support.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by woniper on 15. 9. 1..
 */
public interface BoardService {

    Page<Board> getBoard(Pageable pageable);

    Board getBoard(Long id);

    boolean deleteBoard(Long id);

    Board updateBoard(Long id, BoardDto.Request boardDto);

    Board createBoard(BoardDto.Request boardDto);
}
