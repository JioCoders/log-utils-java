package com.jiocoders.config;

import com.jiocoders.entity.AuditApiLogEntity;
import com.jiocoders.entity.AuditErrLogEntity;
import com.jiocoders.entity.AuditMsgLogEntity;
import com.jiocoders.repository.ApiLogJpaRepository;
import com.jiocoders.repository.BufferedRepository;
import com.jiocoders.repository.ErrLogJpaRepository;
import com.jiocoders.repository.MsgLogJpaRepository;

@Configuration
public class BufferedRepositoryConfig {

    @Bean
    public BufferedRepository<AuditApiLogEntity> apiLogBufferedRepository(ApiLogJpaRepository repository) {
        return new BufferedRepository<>(repository);
    }

    @Bean
    public BufferedRepository<AuditMsgLogEntity> msgLogBufferedRepository(MsgLogJpaRepository repository) {
        return new BufferedRepository<>(repository);
    }

    @Bean
    public BufferedRepository<AuditErrLogEntity> errLogBufferedRepository(ErrLogJpaRepository repository) {
        return new BufferedRepository<>(repository);
    }

}
