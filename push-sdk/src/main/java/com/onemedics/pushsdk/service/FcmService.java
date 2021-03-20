package com.onemedics.pushsdk.service;

import com.google.firebase.messaging.*;
import com.onemedics.pushsdk.payload.request.PushNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class FcmService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessage(request);

        //kafka 를 보낸다
        sendMessagePush(request.getTopic(), request);

        //성공한다면 consumer 하고 보여준다.
        String response = sendAndGetResponse(message);
        log.info(
                "Sent message to token. Device token: {}, {}, response {}",
                request.getToken(),
                request.getTopic(),
                response);
    }


    public void sendMessagePush(String topic, Object message) throws NullPointerException {
        kafkaTemplate.send(topic, message);
        //todo 성공 실패에 대한 값이 필요
    }


    private String sendAndGetResponse(Message message)
            throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis())
                .setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setTag(topic).build())
                .build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build())
                .build();
    }

    private Message getPreconfiguredMessage(PushNotificationRequest request) {
        return Objects.nonNull(request.getToken())
                ? getPreconfiguredMessageBuilder(request)
                .setToken(request.getToken())
                .putAllData(request.getData())
                .build()
                : getPreconfiguredMessageBuilder(request)
                .setTopic(request.getTopic())
                .putAllData(request.getData())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .putAllData(request.getData())
                .setNotification(
                        Notification.builder()
                                .setTitle(request.getTitle())
                                .setBody(request.getMessage())
                                .build());
    }
}
