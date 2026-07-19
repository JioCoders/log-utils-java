package com.jiocoders.repository;

import com.jiocoders.entity.AuditErrLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ErrLogJpaRepository extends JpaRepository<AuditErrLogEntity, UUID> {
}
