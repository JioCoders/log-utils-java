package com.example.logger.config;

import com.example.logger.entity.AuditApiLogEntity;
import com.example.logger.entity.AuditErrLogEntity;
import com.example.logger.entity.AuditMsgLogEntity;
import com.example.logger.repository.ApiLogJpaRepository;
import com.example.logger.repository.ErrLogJpaRepository;
import com.example.logger.repository.BufferedRepository;
import com.example.logger.repository.MsgLogJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
