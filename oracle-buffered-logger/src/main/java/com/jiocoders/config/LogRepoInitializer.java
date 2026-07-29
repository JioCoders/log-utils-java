package com.jiocoders.config;

import com.jiocoders.LogPrinter;
import com.jiocoders.entity.AuditApiLogEntity;
import com.jiocoders.entity.AuditErrLogEntity;
import com.jiocoders.entity.AuditMsgLogEntity;
import com.jiocoders.repository.ApiLogJpaRepository;
import com.jiocoders.repository.BufferedRepository;
import com.jiocoders.repository.ErrLogJpaRepository;
import com.jiocoders.repository.MsgLogJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LogRepoInitializer {

    private final BufferedRepository bufferedRepository;
    private final ApiLogJpaRepository apiLogJpaRepository;
    private final ErrLogJpaRepository errLogJpaRepository;
    private final MsgLogJpaRepository msgLogJpaRepository;

    @PostConstruct
    public void init() {
        new LogPrinter();
        bufferedRepository.registerRepository(AuditApiLogEntity.class, apiLogJpaRepository);
        bufferedRepository.registerRepository(AuditErrLogEntity.class, errLogJpaRepository);
        bufferedRepository.registerRepository(AuditMsgLogEntity.class, msgLogJpaRepository);
    }
}



