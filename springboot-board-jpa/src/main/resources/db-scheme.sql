create table board(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(45) NOT NULL,
  content VARCHAR(255) NOT NULL,
  create_date TIMESTAMP DEFAULT now()
);