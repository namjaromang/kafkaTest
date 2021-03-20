package com.onemedics.pushapi.graphql.query;

import com.onemedics.pushapi.entity.UserTokenEntity;
import com.onemedics.pushapi.repository.UserTokenRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.CustomConnection;
import graphql.relay.CustomListConnection;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserTokenQuery implements GraphQLQueryResolver {
    private final UserTokenRepository usertokenRepository;

    @PreAuthorize("isAuthenticated()")
    public CustomConnection<UserTokenEntity> userTokens(
            int first, int page, DataFetchingEnvironment env) {
        return new CustomListConnection<>(usertokenRepository.findAll(PageRequest.of(page, first)))
                .get(env);
    }
}
