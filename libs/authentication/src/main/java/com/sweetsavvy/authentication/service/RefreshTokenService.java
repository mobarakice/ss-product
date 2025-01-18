package com.sweetsavvy.authentication.service;

import com.sweetsavvy.authentication.entity.RefreshTokenEntity;
import com.sweetsavvy.authentication.model.RefreshTokenRequestDto;
import com.sweetsavvy.authentication.model.RefreshTokenResponse;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshTokenEntity> findByToken(String token);

    RefreshTokenEntity createRefreshToken(String userName);

    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);

    void deleteByUserName(String userName);

    RefreshTokenResponse accessTokenGenerateUsingRefreshToken(RefreshTokenRequestDto request);
}
