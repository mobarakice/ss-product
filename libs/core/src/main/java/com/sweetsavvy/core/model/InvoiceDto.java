package com.sweetsavvy.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record InvoiceDto(
        @JsonIgnore
        String id,
        String customerId,
        Integer amount,
        String status,
        @JsonIgnore
        String date
) {
}
