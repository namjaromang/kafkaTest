package com.onemedics.pushapi.graphql.mutation;

import com.onemedics.pushapi.entity.UserTokenEntity;
import com.onemedics.pushapi.repository.UserTokenRepository;
import com.onemedics.pushapi.service.JwtService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserTokenMutation implements GraphQLMutationResolver {
    private final UserTokenRepository usertokenRepository;
    private final JwtService jwtService;

    @PreAuthorize("isAuthenticated()")
    @Transactional
    public UserTokenEntity createUserToken(UserTokenEntity data)
            throws IOException, GeneralSecurityException {
        Map<String, Object> userInfo = jwtService.getJwtInfo();
        Integer userId = (Integer) userInfo.get("uid");
        String username = String.valueOf(userInfo.get("name"));

        data.setUserId(userId);
        data.setUsername(username);

        // 최초 로그인 시에 호출되므로 활성화상태이며 탈퇴가 안된상태임
        data.setIsEnabled(true);
        data.setWithdraw(true);

        data.setCreatedUserId(userId);
        data.setUpdatedUserId(userId);

        return usertokenRepository.save(data);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    public void deleteUserToken(long userTokenId) {
        usertokenRepository.deleteById(userTokenId);
    }


}
