package com.sweetsavvy.authentication.repository;

import com.sweetsavvy.authentication.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);

    void deleteRefreshTokenByUserName(String userName);

    Optional<RefreshTokenEntity> findByUserName(String userName);
}
