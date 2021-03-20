package com.onemedics.pushapi.repository;

import com.onemedics.pushapi.entity.SendMessageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendMessageRepository
        extends PagingAndSortingRepository<SendMessageEntity, Long> {}
