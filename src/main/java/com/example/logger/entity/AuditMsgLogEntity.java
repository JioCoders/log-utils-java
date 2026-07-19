package com.example.logger.entity;

import com.example.logger.utils.LogType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "AUDIT_MSG_LOG")
@SuperBuilder(toBuilder = true)
public class AuditMsgLogEntity extends AuditDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private LogType type;
    private String key;
    private String message;

}

