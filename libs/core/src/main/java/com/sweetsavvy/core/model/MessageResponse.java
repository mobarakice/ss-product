package com.sweetsavvy.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MessageResponse(String message, Long id) {
}
