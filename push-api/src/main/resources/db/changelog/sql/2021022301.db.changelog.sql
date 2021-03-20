--liquibase formatted sql
--changeset jaren:2021022301
--comment 역정규화 컬럼 추가

ALTER TABLE user_token
    ADD username varchar(50) NULL COMMENT '사용자이름' after user_id;

ALTER TABLE user_token
    ADD phone varchar(20) NULL COMMENT '전화번호' after username;

ALTER TABLE user_token
    ADD is_enabled boolean NULL COMMENT '유저활성화여부' after phone;

ALTER TABLE user_token
    ADD withdraw varchar(50) NULL COMMENT '사용자이름' after is_enabled;

ALTER TABLE send_message
    ADD sender_name varchar(50) NULL COMMENT '사용자이름' after send_user_id;

ALTER TABLE send_message
    MODIFY data varchar(4096) null comment '데이터, { "data" : { "movePage":"payment", "moveId":"123123" } }';

ALTER TABLE receiver
    ADD receiver_name varchar(50) null comment '수신자명';
