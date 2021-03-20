package com.onemedics.pushapi.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 스케줄종류 */
public enum TestUser {
    TEST_THERAPIST(
            1000,
            new ArrayList<String>(
                    Arrays.asList("ROLE_USER", "ROLE_DOSOO_USER", "ROLE_DOSOO_THERAPIST"))),
    TEST_PATIENT(
            1001,
            new ArrayList<String>(
                    Arrays.asList("ROLE_USER", "ROLE_DOSOO_USER", "ROLE_DOSOO_PATIENT"))),
    TEST_MASTER(
            1002,
            new ArrayList<String>(
                    Arrays.asList(
                            "ROLE_USER",
                            "ROLE_DOSOO_USER",
                            "ROLE_DOSOO_THERAPIST_MASTER",
                            "ROLE_DOSOO_THERAPIST"))),
    TEST_ADMIN(
            1003,
            new ArrayList<String>(
                    Arrays.asList("ROLE_USER", "ROLE_DOSOO_USER", "ROLE_DOSOO_ADMIN_MASTER")));

    TestUser(int userId, List<String> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

    int userId;
    List<String> authorities;

    public int getUserId() {
        return userId;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
