package com.onemedics.pushapi.graphql.resolver;

import com.onemedics.pushapi.repository.SendMessageRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendMessageResolver implements GraphQLQueryResolver {
    private final SendMessageRepository sendmessageRepository;
}
