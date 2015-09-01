package net.woniper.spring.board.mybatis.entity;

import java.util.Date;

/**
 * Created by woniper on 15. 9. 1..
 */
public class Board {

    private Long id;
    private String title;
    private String content;
    private Date createDate = new Date();

    public Board() {}

    public Board(String title, String content) {
        setTitle(title);
        setContent(content);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

}
