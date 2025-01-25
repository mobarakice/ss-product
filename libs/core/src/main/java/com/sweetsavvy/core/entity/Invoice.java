package com.sweetsavvy.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;
    private int amount;
    private String status;

    private String date;

    public String getCustomerId() {
        return customer.getId();
    }

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
        this.date = formatInstant(Instant.now());
    }

    public String formatInstant(Instant instant) {
        return DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.of("UTC"))
                .format(instant);
    }
}