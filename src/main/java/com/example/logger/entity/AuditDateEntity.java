package com.example.logger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Audited
@MappedSuperclass
@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class AuditDateEntity implements Serializable {

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Long createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT", nullable = false)
    private Long updatedAt;
}