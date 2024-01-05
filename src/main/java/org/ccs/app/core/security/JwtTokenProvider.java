package org.ccs.app.core.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private int jwtExpirationInMs;

    // 사용자 ID를 기반으로 JWT 토큰을 생성
    public String generateToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        // JWT 토큰을 생성
        return Jwts.builder()
                .setSubject(Long.toString(userId)) // 토큰 subject를 사용자 ID로 설정
                .setIssuedAt(now)
                .setExpiration(expiryDate) // 만료시간 1시간
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // 토큰 서명
                .compact(); // JWT 토큰을 문자열로 압축하여 반환
    }
}
