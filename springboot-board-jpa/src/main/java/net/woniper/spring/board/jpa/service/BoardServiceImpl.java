package net.woniper.spring.board.jpa.service;

import net.woniper.spring.board.jpa.entity.Board;
import net.woniper.spring.board.jpa.repository.BoardRepository;
import net.woniper.spring.board.jpa.support.BoardDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by woniper on 15. 9. 1..
 */
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private ModelMapper modelMapper;

    @Override
    public Page<Board> getBoard(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board getBoard(Long id) {
        return boardRepository.findOne(id);
    }

    @Override
    public boolean deleteBoard(Long id) {
        Board board = getBoard(id);
        if(board != null) {
            boardRepository.delete(board);
            return true;
        }
        return false;
    }

    @Override
    public Board updateBoard(Long id, BoardDto.Request boardDto) {
        Board board = getBoard(id);
        if(board != null) {
            board.update(boardDto);
            return board;
        }

        return null;
    }

    @Override
    public Board createBoard(BoardDto.Request boardDto) {
        Board board = modelMapper.map(boardDto, Board.class);
        return boardRepository.save(board);
    }
}
