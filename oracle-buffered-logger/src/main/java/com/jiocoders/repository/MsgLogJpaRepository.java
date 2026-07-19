package com.jiocoders.repository;

import com.jiocoders.entity.AuditMsgLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MsgLogJpaRepository extends JpaRepository<AuditMsgLogEntity, UUID> {
}
