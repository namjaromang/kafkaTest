package com.onemedics.pushapi.constant;

public class Const {
    public static final int ROLE_USER = 2;
    public static final int ROLE_UNIFIED_USER = 1000;

    public static final String USER_ROLE_DOSOO_USER = "DOSOO_USER"; // 도수유저
    public static final String USER_ROLE_DOSOO_THERAPIST = "ROLE_DOSOO_THERAPIST"; // 도수치료사
    public static final String USER_ROLE_DOSOO_THERAPIST_MASTER =
            "DOSOO_THERAPIST_MASTER"; // 도수마스터치료사
    public static final String USER_ROLE_DOSOO_ADMIN_USER = "ROLE_DOSOO_ADMIN_USER"; // 도수 어드민 유저
    public static final String USER_ROLE_DOSOO_ADMIN_MASTER =
            "ROLE_DOSOO_ADMIN_MASTER"; // 도수 어드민 마스터

    public static final String SMS_AUTH_NUMBER = "025495494";
    public static final String SMS_USER_ID = "onemedics";
    public static final String SMS_AUTH_NUM_MSG = "[도수M] 인증번호 [%s]을 입력 해주세요.";
    public static final String SMS_TEMP_PASSWORD_MSG = "[도수M] 회원님의 임시 비밀번호는 [%s]입니다.";

    // 도수 서비스 권한
    public static final int ROLE_DOSOO_USER = 10000; // 도수 유저
    public static final int ROLE_DOSOO_THERAPIST = 10001; // 도수 치료사
    public static final int ROLE_DOSOO_THERAPIST_MASTER = 10002; // 도수 마스터 치료사
    public static final int ROLE_DOSOO_PATIENT = 10005;

    public static final String OAUTH_CLIENT_DOSOO_APP = "dosoo-app";
    public static final String OAUTH_CLIENT_DOSOO_WEB = "dosoo-web";
}
