package com.onemedics.pushapi.repository;

import com.onemedics.pushapi.entity.UserTokenEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends PagingAndSortingRepository<UserTokenEntity, Long> {}
