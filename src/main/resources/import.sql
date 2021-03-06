
drop table address ;

drop table user;

drop table user_group;


CREATE TABLE user_group (
  GROUP_ID BIGINT(16) NOT NULL,
  GROUP_NAME varchar(50) DEFAULT NULL,
  PRIMARY KEY (GROUP_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE user (
  EMAIL varchar(50) NOT NULL,
  GROUP_ID BIGINT(16) NOT NULL,
  NAME varchar(50) DEFAULT NULL,
  AGE int(3) NOT NULL,
  USE_YN char(1) DEFAULT NULL,
  REG_USER varchar(10) DEFAULT NULL,
  PRIMARY KEY (EMAIL),
  CONSTRAINT FK_USER_GROUP_ID_FOR_USER_GROUP_GROUP_ID FOREIGN KEY (GROUP_ID) REFERENCES user_group (GROUP_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE address (
  EMAIL varchar(50) NOT NULL,
  ADDRESS varchar(200) NOT NULL,
  COMMENTS varchar(200) DEFAULT NULL,
  PRIMARY KEY (EMAIL,ADDRESS),
  CONSTRAINT FK_ADDRESS_EMAIL_FOR_USER_EMAIL FOREIGN KEY (EMAIL) REFERENCES user (EMAIL)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO user_group (GROUP_ID, GROUP_NAME)
VALUES (1, 'T3Q GROUP');

INSERT INTO user_group (GROUP_ID, GROUP_NAME)
VALUES (2, 'YNA GROUP');


INSERT INTO user(EMAIL,GROUP_ID, NAME,   AGE,  USE_YN, REG_USER)
VALUES ('warmpark@t3q.com', 1,'박병훈',44,'Y', 'warmpark');

INSERT INTO user(EMAIL,GROUP_ID, NAME,   AGE,  USE_YN, REG_USER)
VALUES ('minsugar@gmail.com',1, '정민숙',40,'Y', 'minsugar');



INSERT INTO address (EMAIL, ADDRESS, COMMENTS)
VALUES ('warmpark@t3q.com', '서울시 구로구 벽산7차', '서울');

INSERT INTO address (EMAIL, ADDRESS, COMMENTS)
VALUES ('warmpark@t3q.com', '서울시 종로구 수송동 연합뉴스', '서울');


INSERT INTO address (EMAIL, ADDRESS, COMMENTS)
VALUES ('warmpark@t3q.com', '경기도 고양시 일산동구 식사동 위시티자이파트', '경기도');


