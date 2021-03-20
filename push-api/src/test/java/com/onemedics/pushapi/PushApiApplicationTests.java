package com.onemedics.pushapi;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PushApiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(RandomStringUtils.randomAlphanumeric(8));
    }
}
