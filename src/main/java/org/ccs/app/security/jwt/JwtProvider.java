package org.ccs.app.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


@Component // Spring 컨테이너에 빈으로 등록
public class JwtProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // 사용자 인증 정보를 받아 JWT 토큰 생성
    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(authentication.getName()) // 토큰의 'subject'를 사용자 이름으로 설정
                .setIssuedAt(now) // 토큰의 'issued at' 시간을 현재 시간으로 설정
                .setExpiration(expiryDate) // 토큰의 만료 시간 설정
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // HS512 알고리즘과 비밀키로 서명
                .compact(); // JWT 토큰을 문자열로 압축하여 반환
    }

    // 전달받은 JWT 토큰의 유효성을 검증합니다.
    public boolean validateToken(String authToken) {
        try {
            // 토큰 파싱 및 검증
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            // 토큰 검증 실패 시 예외 처리. 로깅 등의 추가 작업
        }
        return false; // 토큰이 유효하지 않을 경우 false 반환
    }

    // JWT 토큰에서 사용자 이름을 추출
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret) // 서명 키 설정
                .parseClaimsJws(token) // 토큰 파싱
                .getBody(); // 클레임(토큰의 내용)을 가져옴

        return claims.getSubject(); // 클레임에서 'subject' (사용자 이름)를 반환
    }
}
