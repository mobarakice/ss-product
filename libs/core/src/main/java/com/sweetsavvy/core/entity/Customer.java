package com.sweetsavvy.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
    @JsonProperty("image_url")
    private String imageUrl;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}