package org.ccs.app.entrypoints.login.service;

import org.ccs.app.core.security.domain.RefreshToken;
import org.ccs.app.core.security.infra.RefreshTokenRepository;
import org.ccs.app.core.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(User user, RefreshToken refreshToken) {
        // 리프레시 토큰을 암호화하여 저장하는 로직 구현
        String encryptedToken = encryptToken(refreshToken.getToken());
        refreshToken.setToken(encryptedToken);
        refreshTokenRepository.save(refreshToken);
    }

    public boolean isRefreshTokenValid(String token) {
        // 암호화된 토큰으로 데이터베이스에서 조회
        String encryptedToken = encryptToken(token);
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(encryptedToken);

        // 리프레시 토큰이 존재하고, 만료되지 않았는지 확인
        return refreshToken.isPresent() && !isTokenExpired(refreshToken.get());
    }

    private boolean isTokenExpired(RefreshToken refreshToken) {
        // 현재 날짜와 토큰의 만료 날짜를 비교하여 만료 여부 판단
        return refreshToken.getExpiryDate().isBefore(LocalDateTime.now());
    }

    private String encryptToken(String token) {
        // 리프레시 토큰을 암호화하는 로직 구현
        // 암호화된 토큰 반환
    }
}
