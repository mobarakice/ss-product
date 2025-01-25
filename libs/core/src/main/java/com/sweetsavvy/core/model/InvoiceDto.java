package com.sweetsavvy.core.model;

public record InvoiceDto(
        String customerId,
        Integer amount,
        String status
) {
}
