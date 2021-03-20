package com.onemedics.pushapi.graphql.mutation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.onemedics.pushapi.client.UserClient;
import com.onemedics.pushapi.payload.response.UserRoleResponse;
import com.onemedics.pushapi.service.JwtService;
import com.onemedics.pushapi.test.TestUser;
import com.onemedics.pushapi.test.TestUtil;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTokenMutationTest {
    @Autowired private GraphQLTestTemplate graphQLTestTemplate;
    @MockBean JwtService jwtServiceMock;
    @MockBean UserClient userClientMock;

    //    static UserEntity userEntity = new UserEntity();
    //    static ApiEntityResponse apiEntityResponse = new ApiEntityResponse(null, null,
    // userEntity);
    static Map<String, Object> userInfo = new HashMap<>();
    static String accessToken = "";

    @BeforeEach
    void setUp() throws IOException {
        userInfo.put("uid", TestUser.TEST_PATIENT.getUserId());
        userInfo.put("authorities", TestUser.TEST_PATIENT.getAuthorities());

        accessToken = TestUtil.getOauthToken();
    }

    @Test
    @DisplayName("사용자 토큰 추가")
    void createUserToken() throws IOException, GeneralSecurityException {
        doReturn(userInfo).when(jwtServiceMock).getJwtInfo();
        UserRoleResponse u = new UserRoleResponse();
        u.setUserId(TestUser.TEST_PATIENT.getUserId());
        u.setRoleEngNames(TestUser.TEST_PATIENT.getAuthorities());
        doReturn(u).when(userClientMock).findUserRole(TestUser.TEST_PATIENT.getUserId());

        graphQLTestTemplate.addHeader("Authorization", "Bearer " + accessToken);
        GraphQLResponse response =
                graphQLTestTemplate.postForResource(
                        "graphql/userToken/userTokenMutationCreateUserToken.graphql");
        Exception e =
                assertThrows(
                        com.jayway.jsonpath.PathNotFoundException.class,
                        () -> response.get("$.errors[0].message"));
        System.out.println(e.getMessage());
        assertTrue(response.isOk());
    }
}
