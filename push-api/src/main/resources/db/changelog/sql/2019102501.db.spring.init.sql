--liquibase formatted sql

--changeset jaren:2019102501

/**
 * spring 에서 필요한 테이블(인증 & sequence)
 */

/* jpa 시퀀스 테이블 */
DROP TABLE IF EXISTS `hibernate_sequence`;

create table hibernate_sequence
(
  next_val bigint null
)
  engine = MyISAM;

/* id값들을 1억 이상으로 디폴트 셋팅 */
insert into hibernate_sequence (next_val) values (100000000);
