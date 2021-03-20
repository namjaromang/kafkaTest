package com.onemedics.pushapi.graphql.resolver;

import com.onemedics.pushapi.repository.UserTokenRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserTokenResolver implements GraphQLQueryResolver {
    private final UserTokenRepository usertokenRepository;
}
