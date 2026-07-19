package com.jiocoders.entity;

import com.example.logger.utils.LogType;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "AUDIT_ERR_LOG")
@SuperBuilder()
public class AuditErrLogEntity extends AuditDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private LogType type;
    private String key;
    private String message;

}
