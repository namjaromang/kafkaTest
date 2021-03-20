package com.onemedics.pushapi.payload.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleResponse {
    private long userId;
    private List<String> roleEngNames;
}
