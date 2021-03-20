package com.onemedics.pushsdk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerServiceTest {
    @Autowired
    ConsumerService consumerService;

    @Test
    void consume() {
        consumerService.consume("test");
    }
}