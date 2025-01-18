package com.sweetsavvy.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface LatestInvoice {
    String getId();
    String getName();
    @JsonProperty("image_url")
    String getImageUrl();
    String getEmail();
    String getAmount();
}
