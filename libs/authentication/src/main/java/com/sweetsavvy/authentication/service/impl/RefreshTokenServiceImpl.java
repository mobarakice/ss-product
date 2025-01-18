package com.sweetsavvy.authentication.service.impl;

import com.sweetsavvy.authentication.entity.RefreshTokenEntity;
import com.sweetsavvy.authentication.model.RefreshTokenRequestDto;
import com.sweetsavvy.authentication.model.RefreshTokenResponse;
import com.sweetsavvy.authentication.repository.RefreshTokenRepository;
import com.sweetsavvy.authentication.service.RefreshTokenService;
import com.sweetsavvy.authentication.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${waf.app.refresh.jwtExpirationMs}")
    private int jwtRefreshExpirationMs;

    private final JwtUtil jwtUtils;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshTokenEntity createRefreshToken(String userName) {
        var optionalRefreshToken = refreshTokenRepository.findByUserName(userName);
        var refreshToken = optionalRefreshToken.orElseGet(RefreshTokenEntity::new);
        refreshToken.setUserName(userName);
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
        refreshToken.setToken(jwtUtils.generateToken(userName));
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (jwtUtils.isTokenExpired(token.getToken())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token was expired");
        }
        return token;
    }

    @Transactional
    @Override
    public void deleteByUserName(String userName) {
        refreshTokenRepository.deleteRefreshTokenByUserName(userName);
    }

    @Override
    public RefreshTokenResponse accessTokenGenerateUsingRefreshToken(RefreshTokenRequestDto request) {
        var requestRefreshToken = request.refreshToken();
        return findByToken(requestRefreshToken)
                .map(this::verifyExpiration)
                .map(RefreshTokenEntity::getUserName)
                .map(userId -> {
                    //String token = jwtUtils.generateToken(userId);
                    return new RefreshTokenResponse("jsadjakdja", requestRefreshToken);
                })
                .orElseThrow(() -> new RuntimeException("Refresh token was expired"));
    }
}