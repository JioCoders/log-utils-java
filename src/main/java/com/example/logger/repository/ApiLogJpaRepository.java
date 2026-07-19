package com.example.logger.repository;

import com.example.logger.entity.AuditApiLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiLogJpaRepository extends JpaRepository<AuditApiLogEntity, UUID> {
}
