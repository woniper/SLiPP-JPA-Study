package net.woniper.spring.board.jpa.entity;

import lombok.Data;
import net.woniper.spring.board.jpa.support.BoardDto;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by woniper on 15. 9. 1..
 */
@Entity(name = "board")
@Data
public class Board extends AbstractPersistable<Long> {

    private String title;
    private String content;

    @Column(name = "createDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    public void update(BoardDto.Request boardDto) {
        setTitle(boardDto.getTitle());
        setContent(boardDto.getContent());
    }
}
