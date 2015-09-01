package net.woniper.spring.board.mybatis.controller;

import net.woniper.spring.board.mybatis.entity.Board;
import net.woniper.spring.board.mybatis.service.BoardService;
import net.woniper.spring.board.mybatis.support.BoardDto;
import net.woniper.spring.board.mybatis.support.Pageable;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by woniper on 15. 9. 1..
 */
@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired private BoardService boardService;
    @Autowired private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBoard(@RequestBody BoardDto.Request boardDto) {
        Board board = boardService.createBoard(boardDto);
        if(board != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapper.map(board, BoardDto.Response.class));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBoard(@PathVariable("id") Long id,
                                         @RequestBody BoardDto.Request boardDto) {
        Board board = boardService.updateBoard(id, boardDto);

        if(board != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapper.map(board, BoardDto.Response.class));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        if(boardService.deleteBoard(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBoard(@PathVariable("id") Long id) {
        Board board = boardService.getBoard(id);

        if(board != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapper.map(board, BoardDto.Response.class));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBoard(@ModelAttribute("pageable") Pageable pageable) {
        List<Board> boards = boardService.getBoard(pageable);

        if(boards != null && !boards.isEmpty()) {
            List<BoardDto.Response> boardDtos = modelMapper.map(boards,
                    new TypeToken<List<Board>>(){}.getType());
            return ResponseEntity.ok(boardDtos);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
