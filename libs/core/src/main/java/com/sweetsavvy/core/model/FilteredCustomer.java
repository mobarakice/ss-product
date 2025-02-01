package com.sweetsavvy.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface FilteredCustomer {
    String getId();

    String getName();

    @JsonProperty("image_url")
    String getImageUrl();

    String getEmail();

    @JsonProperty("total_invoices")
    int getTotalInvoices();

    @JsonProperty("total_pending")
    int getTotalPending();

    @JsonProperty("total_paid")
    int getTotalPaid();
}
