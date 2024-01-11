package org.ccs.app.entrypoints.login.service;

import org.ccs.app.core.security.domain.RefreshToken;
import org.ccs.app.core.security.infra.RefreshTokenRepository;
import org.ccs.app.core.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private String encryptToken(String token) {
        // 리프레시 토큰을 암호화하는 로직 구현
        // 암호화된 토큰 반환
    }
}
