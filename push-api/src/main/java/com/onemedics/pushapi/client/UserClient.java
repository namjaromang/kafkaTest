package com.onemedics.pushapi.client;

import com.onemedics.pushapi.payload.response.UserRoleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-api", url = "${onemedics.commonAuthUrl}")
public interface UserClient {
    @PostMapping("/v1/user/findUserRole")
    UserRoleResponse findUserRole(long userId);
}
