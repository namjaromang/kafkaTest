package com.onemedics.pushapi.graphql.mutation;

import com.onemedics.pushapi.service.JwtService;
import com.onemedics.pushsdk.payload.request.PushNotificationRequest;
import com.onemedics.pushsdk.service.FcmService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class SendMessageMutation {
    private final FcmService fcmService;
    private final JwtService jwtService;

    public void sendPushNotification(PushNotificationRequest pushNotificationRequest) throws IOException, GeneralSecurityException, ExecutionException, InterruptedException {
        Map<String, Object> userInfo = jwtService.getJwtInfo();
        Integer userId = (Integer) userInfo.get("uid");
        String username = String.valueOf(userInfo.get("name"));

        fcmService.sendMessage(pushNotificationRequest);
        //성공이면?? 성공이면 그데이터가 뭔데? consumer 에서 꺼내서 보여주자

    }
}
