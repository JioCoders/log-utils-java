package com.example.logger.repository;

import com.example.logger.entity.AuditErrLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ErrLogJpaRepository extends JpaRepository<AuditErrLogEntity, UUID> {
}
