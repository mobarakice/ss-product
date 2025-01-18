package com.sweetsavvy.authentication.model;

public record RefreshTokenResponse(String accessToken, String refreshToken, String tokenType) {
    public RefreshTokenResponse(String accessToken, String refreshToken) {
        this(accessToken, refreshToken, "Bearer");
    }
}
