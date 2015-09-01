package net.woniper.spring.board.mybatis.support;

import lombok.Data;

import java.util.Date;

/**
 * Created by woniper on 15. 9. 1..
 */
@Data
public class BoardDto {

    private String title;
    private String content;

    @Data
    public static class Request extends BoardDto {
    }

    @Data
    public static class Response extends BoardDto {
        private Long id;
        private Date createDate;
    }
}
