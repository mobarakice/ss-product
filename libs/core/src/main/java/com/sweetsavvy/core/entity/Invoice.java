package com.sweetsavvy.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

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
//@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unrecognized fields
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

    @Transient
    private String customer_id;

    public String getCustomer_id() {
        return customer_id;
    }

    public String getCustomerId() {
        return customer.getId();
    }

    @PrePersist
    public void generateId() {
        if(Strings.isBlank(id)) {
            this.id = UUID.randomUUID().toString();
        }
        if(Strings.isBlank(date)) {
            this.date = formatInstant(Instant.now());
        }

    }

    public String formatInstant(Instant instant) {
        return DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.of("UTC"))
                .format(instant);
    }
}