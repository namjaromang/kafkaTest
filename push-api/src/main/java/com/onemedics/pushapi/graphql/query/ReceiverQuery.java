package com.onemedics.pushapi.graphql.query;

import com.onemedics.pushapi.entity.ReceiverEntity;
import com.onemedics.pushapi.repository.ReceiverRepository;
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
public class ReceiverQuery implements GraphQLQueryResolver {
    private final ReceiverRepository receiverRepository;

    @PreAuthorize("isAuthenticated()")
    public CustomConnection<ReceiverEntity> receivers(
            int first, int page, DataFetchingEnvironment env) {
        return new CustomListConnection<>(receiverRepository.findAll(PageRequest.of(page, first)))
                .get(env);
    }
}
