package com.zabisoft.alarm_app.service;

import com.zabisoft.alarm_app.entities.RefreshToken;
import com.zabisoft.alarm_app.repositories.RefreshTokenRepository;
import com.zabisoft.alarm_app.util.DateUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
   public RefreshToken createRefreshToken(String email) {
        refreshTokenRepository.deleteByEmail(email);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(
                DateUtil.expiryDateForSevenDays()
        );
        refreshToken.setEmail(email);
        return refreshTokenRepository.save(refreshToken);

   }

   public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken  = refreshTokenRepository.findByToken(token).orElseThrow(
                () -> new RuntimeException("Token Not Found")
        );
        if (refreshToken.isRevoked()) {
            throw new RuntimeException("Token Revoked");
        }

        if (refreshToken.getExpiresAt().before(new Date())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh Token Expired. Please Login again!");
        }
        return refreshToken;
    }
}
