package org.ccs.app.core.security;

import io.jsonwebtoken.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private int jwtExpirationInMs;

    @Value("${jwt.refresh-expiration-ms}")
    private int jwtRefreshExpirationInMs;

    // 사용자 ID를 기반으로 JWT 액세스 토큰을 생성하는 메서드
    public String generateToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        // JJWT 토큰 생성 및 반환
        return Jwts.builder()
                .setSubject(Long.toString(userId)) // 토큰 subject를 사용자 ID로 설정
                .setIssuedAt(now)
                .setExpiration(expiryDate) // 만료시간 1시간
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // 토큰 서명
                .compact(); // JWT 토큰을 문자열로 압축하여 반환
    }

    // 사용자 ID를 기반으로 JWT 리프레시 토큰을 생성하는 메서드
    public String generateRefreshToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtRefreshExpirationInMs);

        // JWT 리프레시 토큰 생성 및 반환
        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // 리프레시 토큰의 유효성을 검증하는 메서드
    public boolean validateRefreshToken(String token) {
        try {
            // 토큰 파싱 및 서명 검증
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);

            // 토큰의 만료 시간 검증
            if (claims.getBody().getExpiration().before(new Date())) {
                return false; // 토큰이 만료된 경우
            }

            return true; // 토큰이 유효한 경우
        } catch (JwtException | IllegalArgumentException e) {
            // 토큰 파싱 중 예외 발생 (유효하지 않은 토큰)
            return false;
        }
    }

    public String generateAccessTokenUsingRefreshToken(String refreshToken) {
        // 리프레시 토큰에서 Claims 추출
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(refreshToken)
                .getBody();

        Long userId = Long.parseLong(claims.getSubject());
        return generateToken(userId);
    }

    public String generateNewRefreshToken(String refreshToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(refreshToken)
                .getBody();

        Long userId = Long.parseLong(claims.getSubject());
        return generateRefreshToken(userId);
    }
}
