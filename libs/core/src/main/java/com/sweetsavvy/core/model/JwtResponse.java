package com.sweetsavvy.core.model;

public record JwtResponse (String token, String type, String username) {
}