package org.ccs.app.core.share.authenticate.token;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Getter
public class JWTUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generate(JWTType type, Long userId) {
        LocalDateTime now = LocalDateTime.now();
        Date issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date expiryDate = new Date(issuedAt.getTime() + type.getExpirationMs());
        return Jwts.builder()
                .setSubject(Long.toString(userId)) // 토큰 subject를 사용자 ID로 설정
                .setIssuedAt(issuedAt)
                .setExpiration(expiryDate) // 만료시간 1시간
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // 토큰 서명
                .compact();
    }

    public boolean verify(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Object decode(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
    }


    // 사용자 ID를 기반으로 JWT 토큰을 생성
    @Deprecated
    public String generateToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600);

        // JWT 토큰을 생성
        return Jwts.builder()
                .setSubject(Long.toString(userId)) // 토큰 subject를 사용자 ID로 설정
                .setIssuedAt(now)
                .setExpiration(expiryDate) // 만료시간 1시간
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // 토큰 서명
                .compact(); // JWT 토큰을 문자열로 압축하여 반환
    }

    @Deprecated
    public String generateRefreshToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 640000);

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
