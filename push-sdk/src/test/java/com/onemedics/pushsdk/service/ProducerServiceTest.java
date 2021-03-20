package com.onemedics.pushsdk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProducerServiceTest {
    @Autowired
    ProducerService producerService;

    @Test
    void sendMessagePush() {
        Map<String, Object> data = new HashMap();
        List test = new ArrayList();
        test.add(1);
        test.add(2);
        test.add(3);
        data.put("test", test);

        producerService.sendMessagePush(data);
    }
}