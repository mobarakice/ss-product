package com.sweetsavvy.core.entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@EqualsAndHashCode
@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE NOT NULL")
    private Boolean isActive = true;

    @Column(name = "created_by")
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
