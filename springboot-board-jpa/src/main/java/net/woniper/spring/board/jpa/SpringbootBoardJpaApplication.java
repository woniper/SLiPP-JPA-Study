package net.woniper.spring.board.jpa;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringbootBoardJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBoardJpaApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("slipp")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("/db-scheme.sql")
                .addScript("/query.sql")
                .build();
    }
}
