--liquibase formatted sql
--changeset jaren:2021022201
--comment 푸시서버 초기테이블 구성

CREATE TABLE `user_token` (
                              `user_token_id`	bigint	NOT NULL	COMMENT '사용자토큰아이디',
                              `user_id`	bigint	NOT NULL	COMMENT '사용자아이디',
                              `device_token`	varchar(255)	NOT NULL	COMMENT '토큰, 디바이스토큰',
    `client_id`	varchar(50)	NOT NULL	COMMENT '클라이언트아이디',
    `device_type`	varchar(50)	NOT NULL	COMMENT '장비타입, IOS,ANDROID,WEB',
    `device_model`	varchar(60)	NULL	COMMENT '장비모델, S960-GN 장비모델명',
    `client_version`	varchar(60)	NULL	COMMENT '클라이언트버전',
    `push_enabled`	boolean	NOT NULL	DEFAULT 1	COMMENT '푸시허용여부',
    `created_at`	datetime	NOT NULL	DEFAULT current_timestamp	COMMENT '생성시간',
    `created_user_id`	bigint	NOT NULL	COMMENT '생성자',
    `updated_at`	datetime	NOT NULL	DEFAULT current_timestamp on update current_timestamp	COMMENT '수정시간',
    `updated_user_id`	bigint	NOT NULL	COMMENT '수정자'
    );

CREATE TABLE `send_message` (
                                `send_message_id`	bigint	NOT NULL	COMMENT '보낸메시지아이디',
                                `send_user_id`	bigint	NULL	COMMENT '보낸사용자아이디',
                                `title`	VARCHAR(255)	NOT NULL	COMMENT '제목',
    `message`	VARCHAR(255)	NOT NULL	COMMENT '내용',
    `data`	VARCHAR(255)	NULL	COMMENT '데이터, { "data" : { "movePage":"payment", "moveId":"123123" } }',
    `send_at`	datetime	NOT NULL	COMMENT '발송일시',
    `send_reservation_at`	datetime	NULL	COMMENT '예약일시',
    `send_success`	boolean	NOT NULL	DEFAULT 0	COMMENT '성공여부, 0:실패, 1:성공',
    `created_at`	datetime	NOT NULL	DEFAULT current_timestamp	COMMENT '생성시간',
    `created_user_id`	bigint	NOT NULL	COMMENT '생성자'
    );

ALTER TABLE `user_token` ADD CONSTRAINT `PK_USER_TOKEN` PRIMARY KEY (
	`user_token_id`
);

ALTER TABLE `send_message` ADD CONSTRAINT `PK_SEND_MESSAGE` PRIMARY KEY (
	`send_message_id`
);

CREATE TABLE `receiver` (
                            `receiver_id`	bigint	NOT NULL	COMMENT '수신자목록아이디',
                            `send_message_id`	bigint	NOT NULL	COMMENT '보낸메시지아이디',
                            `receive_user_id`	bigint	NOT NULL	COMMENT '받은사용자아이디',
                            `read`	boolean	NOT NULL	DEFAULT 0	COMMENT '읽음여부, IOS는 기능미제공',
                            `receive_at`	datetime	NULL	COMMENT '수신시간',
                            `created_at`	datetime	NOT NULL	COMMENT '생성시간',
                            `created_user_id`	bigint	NOT NULL	COMMENT '생성자',
                            `updated_at`	datetime	NOT NULL	COMMENT '수정시간',
                            `updated_user_id`	bigint	NOT NULL	COMMENT '수정자'
);

ALTER TABLE `receiver` ADD CONSTRAINT `PK_RECEIVER` PRIMARY KEY (
	`receiver_id`
);

ALTER TABLE `receiver` ADD CONSTRAINT `FK_send_message_TO_receiver_1` FOREIGN KEY (
	`send_message_id`
)
REFERENCES `send_message` (
	`send_message_id`
);

create index receiver_receive_user_id_index
    on receiver (receive_user_id);
