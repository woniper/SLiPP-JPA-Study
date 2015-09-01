package net.woniper.spring.board.mybatis.service;

import net.woniper.spring.board.mybatis.entity.Board;
import net.woniper.spring.board.mybatis.mapper.BoardMapper;
import net.woniper.spring.board.mybatis.support.BoardDto;
import net.woniper.spring.board.mybatis.support.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by woniper on 15. 9. 1..
 */
@Service
public class BoardSeviceImpl implements BoardService {

    @Autowired private BoardMapper boardMapper;
    @Autowired private ModelMapper modelMapper;

    @Override
    public Board createBoard(BoardDto.Request boardDto) {
        Board board = modelMapper.map(boardDto, Board.class);
        if(boardMapper.insertBoard(board) > 0) {
            return board;
        }
        return null;
    }

    @Override
    public Board updateBoard(Long id, BoardDto.Request boardDto) {
        Board board = getBoard(id);
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        if(boardMapper.updateBoard(board) > 0) {
            return board;
        }
        return null;
    }

    @Override
    public boolean deleteBoard(Long id) {
        return boardMapper.deleteBoard(id) > 0;
    }

    @Override
    public Board getBoard(Long id) {
        return boardMapper.findBoard(id);
    }

    @Override
    public List<Board> getBoard(Pageable pageable) {
        if(pageable.getSize() == 0)
            pageable.setSize(20);

        return boardMapper.findBoards(pageable);
    }
}
