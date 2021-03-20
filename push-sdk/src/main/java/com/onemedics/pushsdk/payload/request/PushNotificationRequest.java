package com.onemedics.pushsdk.payload.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class PushNotificationRequest {
    private String token;
    private String topic;
    private String title;
    private String message;
    private String sendReservationAt;

    private Map<String, String> data;
}


