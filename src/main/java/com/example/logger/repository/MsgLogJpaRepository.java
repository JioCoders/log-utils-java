package com.example.logger.repository;

import com.example.logger.entity.AuditMsgLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MsgLogJpaRepository extends JpaRepository<AuditMsgLogEntity, UUID> {
}
