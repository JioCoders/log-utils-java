package com.jiocoders.repository;

import com.jiocoders.entity.AuditApiLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiLogJpaRepository extends JpaRepository<AuditApiLogEntity, UUID> {
}
