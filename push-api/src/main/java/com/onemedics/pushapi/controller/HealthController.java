package com.onemedics.pushapi.controller;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthController {
    @GetMapping("/check")
    public String check() {
        return "200";
    }

    @GetMapping("/now")
    public String now() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }
}
