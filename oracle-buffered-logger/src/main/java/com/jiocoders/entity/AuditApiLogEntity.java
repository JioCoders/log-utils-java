package com.jiocoders.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "AUDIT_API_LOG")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
public class AuditApiLogEntity extends AuditDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    private int version;

    @Column(name = "METHOD", length = 10)
    private String method;

    private String endpoint;
    private String headers;
    private Integer statusCode;
    private String message;
    private String remark;

    @Column(name = "REQUEST_TIME_MS")
    private Long requestTimeMs;
    @Column(name = "RESPONSE_TIME_MS")
    private Long responseTimeMs;
    @Column(name = "REQUEST_BODY", length = 2000)
    private String requestBody;
    @Column(name = "RESPONSE_BODY", length = 2000)
    private String responseBody;

}
