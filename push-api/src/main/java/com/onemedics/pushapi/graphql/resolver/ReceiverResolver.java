package com.onemedics.pushapi.graphql.resolver;

import com.onemedics.pushapi.repository.ReceiverRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReceiverResolver implements GraphQLQueryResolver {
    private final ReceiverRepository receiverRepository;
}
