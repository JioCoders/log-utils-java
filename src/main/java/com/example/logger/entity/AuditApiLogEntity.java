package com.example.logger.entity;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "ENDPOINT", length = 255)
    private String endpoint;

    @Column(name = "HEADERS", length = 255)
    private String headers;

    @Column(name = "STATUS_CODE")
    private Integer statusCode;

    @Column(name = "REQUEST_TIME_MS")
    private Long requestTimeMs;

    @Column(name = "RESPONSE_TIME_MS")
    private Long responseTimeMs;

    @Column(name = "REQUEST_BODY", length = 2000)
    private String requestBody;

    @Column(name = "RESPONSE_BODY", length = 2000)
    private String responseBody;

}
