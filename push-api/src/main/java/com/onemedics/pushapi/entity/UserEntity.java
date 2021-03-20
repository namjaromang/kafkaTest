package com.onemedics.pushapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 인증서버에 존재하는 entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private long userId;
    private String loginId;
    private String password;
    private String username;
    private String phone;
    private int marketingAgreement;
    private boolean isEnabled = false;
    private boolean withdraw = false;
    private String naverUserId;
    private String facebookUserId;
    private String kakaoUserId;
    private String appleUserId;
    private java.sql.Timestamp lastLoginDt;
    private java.sql.Timestamp createDt;
    private long createUserId;
    private java.sql.Timestamp updateDt;
    private long updateUserId;
}
