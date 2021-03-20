package com.onemedics.pushapi.repository;

import com.onemedics.pushapi.entity.ReceiverEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends PagingAndSortingRepository<ReceiverEntity, Long> {}
