DROP TABLE IF EXISTS midterm;

CREATE TABLE midterm (
  seq           bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name          varchar(10) NOT NULL,
  subject       varchar(50) NOT NULL,
  score         int NOT NULL DEFAULT 0
);

-- 수학
INSERT INTO midterm(name, subject, score) VALUES ('여우', '수학', 50);
INSERT INTO midterm(name, subject, score) VALUES ('늑대', '수학', 80);
INSERT INTO midterm(name, subject, score) VALUES ('돼지', '수학', 70);

-- 국어
INSERT INTO midterm(name, subject, score) VALUES ('여우', '국어', 75);
INSERT INTO midterm(name, subject, score) VALUES ('늑대', '국어', 80);
INSERT INTO midterm(name, subject, score) VALUES ('돼지', '국어', 92);

-- 영어
INSERT INTO midterm(name, subject, score) VALUES ('여우', '영어', 98);
INSERT INTO midterm(name, subject, score) VALUES ('늑대', '영어', 60);
INSERT INTO midterm(name, subject, score) VALUES ('돼지', '영어', 55);

COMMIT;