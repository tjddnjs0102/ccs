package org.ccs.app.entrypoints.login.service;

import org.ccs.app.core.security.domain.RefreshToken;
import org.ccs.app.core.security.infra.RefreshTokenRepository;
import org.ccs.app.core.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
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
        try {
            // MessageDigest 인스턴스를 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 전달받은 토큰 문자열을 바이트 배열로 변환하고, SHA-256 알고리즘으로 해싱
            byte[] hash = digest.digest(token.getBytes());

            // 해시된 데이터를 Base64 문자열로 인코딩
            // Base64 인코딩은 바이트 데이터를 문자열 형태로 변환하는 데 사용
            // 변환된 문자열은 데이터베이스에 저장하기 적합
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 알고리즘이 사용이 불가능한 경우 예외가 발생합니다.
            throw new RuntimeException("Failed to encrypt token", e);
        }
    }
}
