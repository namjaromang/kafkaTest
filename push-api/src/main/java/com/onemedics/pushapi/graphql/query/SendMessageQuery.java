package com.onemedics.pushapi.graphql.query;

import com.onemedics.pushapi.entity.SendMessageEntity;
import com.onemedics.pushapi.repository.SendMessageRepository;
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
public class SendMessageQuery implements GraphQLQueryResolver {
    private final SendMessageRepository sendmessageRepository;

    @PreAuthorize("isAuthenticated()")
    public CustomConnection<SendMessageEntity> sendMessages(
            int first, int page, DataFetchingEnvironment env) {
        return new CustomListConnection<>(
                        sendmessageRepository.findAll(PageRequest.of(page, first)))
                .get(env);
    }
}
