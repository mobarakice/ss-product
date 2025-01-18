package com.sweetsavvy.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "revenue")
public class Revenue {
    @Id
    private String id;
    private String month;
    private int revenue;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}